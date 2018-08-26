     var head = document.head.lastElementChild;

     var ok = document.getElementById('ok-button').addEventListener('click',displayQuestionComponent);
     document.getElementById('course').addEventListener('onchange',displayQuestionComponent)
                                     
     var option = document.getElementById('optionnum');
     var regex = /^[2-5]/
     var form = document.getElementById('the');
     var questionform = document.getElementById('questionform');    
     var questionDiv = document.getElementById('question');
     var questionType ;
     //check the input of the user filter based on the input 
  
  function displayQuestionComponent(e){
  	         questionDiv.innerHTML = "";
         if(form.checkValidity()){
  	       e.preventDefault();
           // get the direction of the course
           // get the question type
           // get the number of option 
           // get display question based on the above choices 
           //change the value of the selected component to the course name and not the rtl
             var optionsLabel = ["A","B","C","D","E"];
             var courseDirection = document.getElementById('course').value;
             questionType = document.getElementById('questiontype').value;
             var optionSize = document.getElementById('optionnum').value;
             var rtlcss = document.getElementById('rtllink');
         
             var createQbtn ;
          
           
                             
               var ques = document.createElement('div');
                var radios="";
              ques.innerHTML = ""; 
              questionDiv.innerHTML = ""; 

                



            if("rtl" === courseDirection){
            	//insert the  rtl css inside the head 
            	var rtlcss = document.createElement('link');
            	rtlcss.id ='rtllink';
            	rtlcss.rel = 'stylesheet';
            	rtlcss.href = 'css/bootstrap-rtl.css';
            	document.head.appendChild(rtlcss);
            }

              if("ltr" === courseDirection && rtlcss !== null){
            	//insert the  rtl css inside the head 
            	rtlcss.remove();
            	
            }
            
             if("multiplechoice" === questionType){
             	console.log('multiplechoice testing ');
                          //multiple choice
                       var ouput = "";    
                       ouput += '<div class="panel-heading main-color-bg ">'+'\n'+
                              '<h3 class="panel-title main-color-bg">Question Setting</h3>'+'\n'+
                               '</div>' +'\n'+
                               '<div class="panel-body"> '+'\n'+
                               '<div class="col-md-12">'+'\n'+

                              '<div class="form-group">'+'\n'+
						       '<label  class="col-sm-2 control-label">Question </label>'+'\n'+
						    '<div class="col-sm-10">'+'\n'+
						    '<input type="text" class="form-control col-sm-10" name ="question-text" required ="required">'+'\n'+
						   ' </div>'+ '\n'+
						 '</div>'
						   var count = 1 ;
						    counter = 0;
                            for(var i = 1 ; i <= optionSize; i++){
                           ouput += '\n'+'<div class="form-group ">'+'\n'+
						  '<label for="inputEmail3" class="col-sm-2 control-label">'+optionsLabel[counter]+' </label>'+'\n'+
						    '<div class="col-sm-10 radio">'+
						 '<input type="radio" name="questionAnswer" class="options"  required ="required">'+'\n'+
						      '<textarea class="form-control col-sm-10"  name="option-'+count+'" required ="required"></textarea>'+'\n'+
	 					'</div>'+'\n'+
	 					'</div>';
                           count++;
	 					counter += 1;
	 					
	 				}

	ouput+=`	
	 					 <div class="form-group">
							  <label for="input" class="col-sm-2 control-label">point</label>
							    <div class="col-sm-10">
							    <input type="number"  name ="point" min="0"  max ="10" class="form-control col-sm-10" id="input" required ="required">
							    </div>
							  </div>
						  <div class="form-group">
						  <label for="inputEmail3" class="col-sm-2 control-label">Feedback </label>
						    <div class="col-sm-10">
						    <input type="text" class="form-control col-sm-10" name ="feedback" id="exampleInputName2 required ="required"">
						    </div>
                           </div>
                           </div>
                           <button type="submit" id="createquestion"  class="btn btn-default col-md-offset-2">Create Question</button>
						    `;
                        
                           
				             
                              ques.innerHTML = ouput;
                              questionDiv.className = 'panel panel-default';
                              questionDiv.appendChild(ques);
           
                            
                 
             }
             
                if("multipleanswers" === questionType){
               //display question Panel
             	console.log('multipleanswers testing');
             	   //multiple choice
                       var ouput = "";    
                       ouput += '<div class="panel-heading main-color-bg ">'+'\n'+
                              '<h3 class="panel-title main-color-bg">Question Setting</h3>'+'\n'+
                               '</div>' +'\n'+
                               '<div class="panel-body"> '+'\n'+
                               '<div class="col-md-12">'+'\n'+

                              '<div class="form-group">'+'\n'+
						       '<label  class="col-sm-2 control-label">Question </label>'+'\n'+
						    '<div class="col-sm-10">'+'\n'+
						    '<input type="text" class="form-control col-sm-10" name ="question-text" required ="required" >'+'\n'+
						   ' </div>'+ '\n'+
						 '</div>'
						    counter = 0;
                            for(var i = 1 ; i <= optionSize; i++){
                           ouput += '\n'+'<div class="form-group ">'+'\n'+
						  '<label for="inputEmail3" class="col-sm-2 control-label">'+optionsLabel[counter]+' </label>'+'\n'+
						    '<div class="col-sm-10 checkbox">'+
						 '<input type="checkbox" name="selectquestionAnswer" class="options"   >'+'\n'+
						      '<textarea class="form-control col-sm-10" name="option-'+i+'" ></textarea>'+'\n'+
	 					'</div>'+'\n'+
	 					'</div>';
	 					counter += 1;
	 				}

	ouput+=`	
	 					  <div class="form-group">
						  <label for="inputEmail3" class="col-sm-2 control-label">point</label>
						    <div class="col-sm-10">
						    <input type="number" name="point" min="0" max ="10" class="form-control col-sm-10" id="exampleInputName2" required ="required">
						    </div>
						  </div>
						  <div class="form-group">
						  <label for="feedback" class="col-sm-2 control-label">Feedback </label>
						    <div class="col-sm-10">
						    <input type="text" name="feedback" class="form-control col-sm-10" id="feedback" required ="required">
						    </div>
						  </div>
						  <div class="form-group">
						  <label for="negpoint" class="col-sm-2 control-label">Negative Point </label>
						    <div class="col-sm-10">
						    <input type="number" name="negpoint" class="form-control col-sm-10" max ="10"  min="0" id="negpoint" required ="required">
						    </div>
						  </div>
						  <button   type="submit" id="createquestion"  class="btn btn-default col-md-offset-2">Create Question</button>
						    `;
                
				              
                              ques.innerHTML = ouput;
                              questionDiv.className = 'panel panel-default';
                              questionDiv.appendChild(ques);
                               var ouput = "";        
                           }
                                      
						                var selCourse = document.getElementById('course');
						                var selectValue = selCourse.options[selCourse.selectedIndex].innerHTML;
						                console.log(selectValue);
						             selCourse.options[selCourse.selectedIndex].value = selectValue ;
						                                      createQbtn = document.getElementById('createquestion');
                                      createQbtn.addEventListener('click', submitForm);

                  }                    
              
  }





     function submitForm(e){    
                        	e.preventDefault();
                    var option = document.getElementsByClassName('options');
                    console.log(option);
                  var count = 0;
                   for(i=0;i < option.length; i++){
		                 
          	         if(option[i].type == 'radio'  && option[i].checked ){
          		 // console.log('radio');
          	        	count += 1;
          		     var inputValue = option[i].nextElementSibling.value;
          			 option[i].value = inputValue;
          			 console.log(option[i].value);
          			 //radios[i].nextElementSibling.style.border = "1px solid red";
          			// alert('fill the input');
          	   }
                 
          	         if(option[i].type == 'checkbox'  && option[i].checked ){
          		 // console.log('radio');
                      count += 1;
          		     var inputValue = option[i].nextElementSibling.value;
          			 option[i].value = inputValue;
          			 console.log(option[i].value);
          			 //radios[i].nextElementSibling.style.border = "1px solid red";
          			// alert('fill the input');
          	   }
//console.log(ans[i].type);
  
  }
       var qtype = document.getElementById('questiontype').value;
       console.log(qtype);
       if( "multipleanswers" === qtype){
       if (count === 0 ) {
        
       alert('checkbox Cannot be empty');  
       }
       
       else{
    	  

           document.qinfo.submit();
           questionDiv.innerHTML = "";
         }
       }
       if("multiplechoice" === qtype){
    	   if (count === 0 ) {
    	        
    	       alert('select radio button');  
    	       }
    	       else{
    	           document.qinfo.submit();
    	           questionDiv.innerHTML = "";
    	         }
       }
     //heleooioeo cjdjd dkjkmdkn kdnijdmnkdn
   



             //check the that all the fields meet requirement and the submit the form.
                //var radios =  document.getElementsByClassName('radio-options').value
            }


