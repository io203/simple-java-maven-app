

def  imageTag = "asia.gcr.io/my-gcp101/my-app:$BUILD_NUMBER"

pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	 
	    dockerHome = tool 'docker'    
	    gcloudHome = tool 'gcloud'         
        PATH = "$PATH:${dockerHome}/bin:${gcloudHome}/bin" 
        
	}
	
	agent {
    kubernetes {      
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
   
  containers:  
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
"""
}
  }
	
	tools {
        maven "maven"
        
    }
     
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
		 	sh "gcloud auth configure-docker"
		 	sh "docker push $registry:$BUILD_NUMBER"
		  	
		    
		  }
		}
		stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }  
        
        stage('Deploy Kubernetes') {
          steps{
          	container('kubectl') {
	            sh("sed -i.bak 's#asia.gcr.io/my-gcp101/my-app:1.0#${imageTag}#' ./k8s/my-app.yaml")
	            sh("kubectl apply  -f k8s/my-app.yaml")
	        }
          }
        }		   
       
    }
}
