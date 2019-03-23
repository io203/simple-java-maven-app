



pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	  
	}
	
	

    agent {
	  label 'docker'
	  
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
        
        
                
       
    }
}
