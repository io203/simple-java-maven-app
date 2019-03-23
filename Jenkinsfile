

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
	
	
    stages {
     
        
        stage('Deploy Kubernetes') {
          steps{
          	container('kubectl') {
	           
	            sh("kubectl apply  -f k8s/my-app.yaml")
	        }
          }
        }		   
       
    }
}
