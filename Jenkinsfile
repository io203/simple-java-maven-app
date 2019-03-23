pipeline {
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	  
	    PATH = "$PATH:/usr/local/bin"
	}
	
    agent any   
    
    stages {
        stage('Build') {           
        	steps {
                sh "kubectl version"
            }
        }        
        
    }
}
