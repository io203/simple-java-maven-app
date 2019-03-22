



def project = 'my-gcp101'
def  appName = 'my-app'
def  feSvcName = "${appName}"
def  imageTag = "asia.gcr.io/my-gcp101/my-app:$BUILD_NUMBER"


pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	  
	}
	

    agent any
    
    tools {
        maven "Maven"
        docker "docker"
    }
    stages {
        stage('Build') {            	   
        	steps {
                sh "mvn -B -DskipTests clean package"
            }
        }        
        
        stage('Building image') {
            steps {                
		       script {
		          dockerImage = docker.build registry + ":$BUILD_NUMBER"
		        }
		        
            }
        }
        
                
        stage('Deploy Image') {
		  steps{
			 script {
			 	dockerImage.push()
			 }
			  
		  }
		}
		stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }
        
        stage('Deploy Kubernetes') {
          steps{
            sh("sed -i.bak 's#asia.gcr.io/my-gcp101/my-app:1.0#${imageTag}#' ./k8s/my-app.yaml")
            sh "kubectl apply -n prd  -f k8s/my-app.yaml"
          }
        }		
    }
}
