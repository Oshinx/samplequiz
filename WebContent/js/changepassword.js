var newPassword =  document.getElementById("new_password")
var rePassword =  document.getElementById("re-enterpass")
function validatePassword(){
if(newPassword.value != rePassword.value){

   console.log("working")
	rePassword.setCustomValidity("Passwords Don't Match");
	
}
else{
	rePassword.setCustomValidity("");
}
}

newPassword.onchange = validatePassword;
rePassword.onkeyup = validatePassword;