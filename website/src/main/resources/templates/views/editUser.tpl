import com.aearwood.coordinator.api.domain.Role;

yieldUnescaped '<!DOCTYPE html>'                                                    
html(lang:'en') {                                                                   
    head {                                                                          
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')       
        title('User Page')                                                            
    }                                                                               
    body {               
   form( role='form', action:'/user/' + user.email + '/edit', method:'post'){  
      input(type:'hidden', name:"${_csrf.parameterName}", value:"${_csrf.token}")
      p('Email') {
        	 label (for:'email', 'Email: ')
        	input(type:'text', name:'email', value:"${user.email}", disabled:true)
        }   
        p('First Name') {
        	 label (for:'firstName', 'First Name: ')
        	input(type:'text', name:'firstName', value:"${user.firstName}")
        }  
        p('Last Name') {
        	 label (for:'lastName', 'Last Name: ')
        	input(type:'text', name:'lastName', value:"${user.lastName}")
        }  
        p('Password') {
        	 label (for:'password', 'Password: ')
        	input(type:'password', name:'password', value:"${user.passwordHash}")
        }
          p('Password Repeated') {
        	 label (for:'passwordRepeated', 'Password Repeated: ')
        	input(type:'text', name:'passwordRepeated', value:"${user.passwordHash}")
        }
        p('Age') {
        	 label (for:'age', 'Age: ')
        	input(type:'number', name:'age', min:0, max:125, value:"${user.age}")
        }
        p('Role') {
        	 label (for:'role', 'Role: ')
        	 br()
        	 
        	 label (for:'PARENT', 'Parent ')
        	 if (user.role == Role.PARENT) {
        		input(type:'radio', name:'role', value:'PARENT' , checked:'checked')
        	}
        	else {
        		input(type:'radio', name:'role', value:'PARENT')
        	}
        	 label (for:'ATHLETE', 'Athlete ')
        	if (user.role == Role.ATHLETE) {
        		input(type:'radio', name:'role', value:'ATHLETE' , checked:'checked')
        	}
        	else {
        		input(type:'radio', name:'role', value:'ATHLETE')
        	}
        	
        	label (for:'BOTH', 'Both ')
        	if (user.role == Role.PARENT_ATHLETE) {
        		input(type:'radio', name:'role', value:'PARENT_ATHLETE' , checked:'checked')
        	}
        	else {
        		input(type:'radio', name:'role', value:'PARENT_ATHLETE')
        	}
        	
        	label (for:'ADMIN', ' Admin ')
        	if (user.role == Role.ADMIN) {
        		input(type:'radio', name:'role', value:'ADMIN' , checked:'checked')
        	}
        	else {
        		input(type:'radio', name:'role', value:'ADMIN')
        	}
        }
        input(type:'submit', name:'Submit')
        }                        
    }                                                                               
}     