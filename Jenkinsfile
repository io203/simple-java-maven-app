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
		     sh "docker push  $registry:$BUILD_NUMBER"
			  echo '========2-2====='
		  }
		}
		stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }
		
		
    }
}
