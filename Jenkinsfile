def  imageTagHub = "saturn203/my-app:$BUILD_NUMBER"
def  imageTag = "328755931990.dkr.ecr.ap-northeast-2.amazonaws.com/my-app:$BUILD_NUMBER"


pipeline {
	environment {
	    registryHub = "saturn203/my-app"
	    registry = "328755931990.dkr.ecr.ap-northeast-2.amazonaws.com/my-app"
	    registryCredential = 'dockerhub'
	    PATH = "$PATH:/usr/local/bin:/Users/blackstar/dev/GCP/SDK/google-cloud-sdk/bin"
	  }
    agent any
   
    
    stages {
        stage('Build') {           
        	steps {
                sh "mvn -B -DskipTests clean package"
            }
        }        
        
        stage('Building image') {
            steps {  
               
		       sh "docker build -t $registry:$BUILD_NUMBER ."
		        
            }
        }
        stage('Deploy Image') {
		  steps{
			echo '========2-1====='
			  sh '''
			    $(aws ecr get-login --no-include-email --region ap-northeast-2)
		     	docker push  $registry:$BUILD_NUMBER
			  '''
			  echo '========2-2====='
			  
		  }
		}
		stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }
        
        stage('Deploy Kubernetes') {
          steps{
            sh("sed -i.bak 's#328755931990.dkr.ecr.ap-northeast-2.amazonaws.com/my-app:1.0#${imageTag}#' ./k8s/my-app.yaml")
            sh "kubectl apply -f k8s/my-app.yaml"
          }
        }
		
		
    }
}
