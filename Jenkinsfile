pipeline {
	environment {
	    registry = "saturn203/my-app"
	    registryCredential = 'dockerhub'
	    PATH = "$PATH:/usr/local/bin"
	  }
    agent any
   
    
    stages {
        stage('Build') {           
        	steps {
                sh 'mvn -B -DskipTests clean package'
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
		      docker.withRegistry( '', registryCredential ) {
		        dockerImage.push()
		      }
		    }
		  }
		}
		stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }
		
		
    }
}
