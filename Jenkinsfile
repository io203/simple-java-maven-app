#!/usr/bin/groovy

timestamps { 
	podTemplate(
	    label: 'je', 
	    inheritFrom: 'default',
	    containers: [
	      containerTemplate(name: 'docker', image: 'docker:18.06', command: 'cat', ttyEnabled: true)
	      ]
	  ) {
	    node ('je') {
	      
	      stage('Deploy Local') {
	        println 'Building docker image'
	        
	
	        
	      } //end deploy local
	
	      
	
	    } // end node
	} // end podTemplate
}