#!/usr/bin/groovy

timestamps { 
	podTemplate(
	    label: 'jenkins-pipeline', 
	    inheritFrom: 'default',
	    containers: [
	      containerTemplate(name: 'docker', image: 'docker:18.06', command: 'cat', ttyEnabled: true)
	      ]
	  ) {
	    node ('jenkins-pipeline') {
	      
	      stage('Deploy Local') {
	        println 'Building docker image'
	        
	
	        
	      } //end deploy local
	
	      
	
	    } // end node
	} // end podTemplate
}