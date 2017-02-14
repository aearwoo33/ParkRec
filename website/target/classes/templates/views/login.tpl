yieldUnescaped '<!DOCTYPE html>'                                                    
html(lang:'en') {                                                                   
    head {                                                                          
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')       
        title('Login Page')                                                            
    }                                                                               
    body {               
    form( role='form', action:'/login', method:'post'){   
      input(type:'hidden', name:"${_csrf.parameterName}", value:"${_csrf.token}")
      p('Email') {
        	 label (for:'email', 'Email: ')
        	input(type:'text', name:'email')
        }   
        p('Password') {
        	 label (for:'password', 'Password: ')
        	input(type:'password', name:'password')
        }
        input(type:'submit', name:'Submit')
        }                        
    }                                                                               
}     