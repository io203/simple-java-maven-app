

def  imageTag = "asia.gcr.io/my-gcp101/my-app:$BUILD_NUMBER"

pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	 
	    dockerHome = tool 'docker'    
	    gcloudHome = tool 'gcloud'    
	         
        PATH = "$PATH:${dockerHome}/bin:${gcloudHome}/bin:/usr/local/bin" 
        
	}
	
	agent any
	
	tools {
        maven "maven"
        
    }
     
    stages {
         
        stage('Deploy Kubernetes') {
          steps{
           
            sh "kubectl apply  -f k8s/my-app.yaml"
          }
        }		   
       
    }
}
