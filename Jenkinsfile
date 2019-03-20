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
		      echo '----login- start------'
		      docker.withRegistry( '', registryCredential ) {
		        echo '----login--success-----'
		        dockerImage.push()
		      }
		      echo '----login--end-----'
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
