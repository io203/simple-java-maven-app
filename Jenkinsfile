



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
      label 'my-app'
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
  - name: java
    image: java:8
    command:
    - cat
    tty: true
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
    
    stages {
        stage('Build') {    
        	agent {
                docker { 
                	image 'maven:3-alpine' 
                }
            }       
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
            sh("sed -i.bak 's#asia.gcr.io/my-gcp101/my-app:1.0#${imageTag}#' ./k8s/my-app.yaml")
            sh "kubectl apply -n prd  -f k8s/my-app.yaml"
          }
        }		
    }
}
