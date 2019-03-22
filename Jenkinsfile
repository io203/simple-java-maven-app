



def project = 'my-gcp101'
def  appName = 'my-app'
def  feSvcName = "${appName}"
def  imageTag = "asia.gcr.io/my-gcp101/my-app:$BUILD_NUMBER"


pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	  
	}
	

    agent {
    kubernetes {
      label 'sample-app'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: gcloud
    image: gcr.io/cloud-builders/gcloud
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
"""
}
  }
    
    tools {
        maven "Maven"
    }
    stages {
        stage('Build') {            	   
        	steps {
                sh "mvn -B -DskipTests clean package"
            }
        }        
        
        stage('Building image & Push') {
            steps {     
            	container('gcloud') {
		          sh "PYTHONUNBUFFERED=1 gcloud builds submit -t ${imageTag} ."
		        }
		        
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
