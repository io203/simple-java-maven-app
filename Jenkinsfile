



pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	 
	    dockerHome = tool 'docker'    
	    gcloudHome = tool 'gcloud'         
        PATH = "$PATH:${dockerHome}/bin:${gcloudHome}/bin:${env.PATH}" 
        registryCredential = 'gcr-docker-auth'
	}
	
	agent any
	
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
            sh("sed -i.bak 's#asia.gcr.io/my-gcp101/my-app:1.0#${imageTag}#' ./k8s/my-app.yaml")
            sh "kubectl apply  -f k8s/my-app.yaml"
          }
        }		   
       
    }
}
