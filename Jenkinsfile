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
		     echo '========1-0====='
               script {
		       echo '========1-1-0====='
		       try{
		          docker.build registry + ":$BUILD_NUMBER"
			       
		       echo '========1-2-1====='
		       }catch(Exception ex){
			  echo '===errror msg:'+ ex.getMessage()       
		       }
			       
		     }
		    echo '========1-3====='
            }
        }
        stage('Deploy Image') {
		  steps{
			echo '========2-1====='
		    script {
		      echo '----login- start------'
		      docker.withRegistry( '', registryCredential ) {
		        echo '----login--success-----'
		        dockerImage.push()
		      }
		      echo '----login--end-----'
		    }
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
