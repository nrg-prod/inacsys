alert('js');
$(document).ready(function() {
	remoteDialog();
	
});

function usenamecheck(){
	var table = document.getElementById('userfom:panelID:checkboxDT_data');
	var namevalidation = /^[a-zA-Z ]{3,30}$/;
	var row;
	for (var i = 0; row = table.rows[i]; i++) {
		var usernamei=document.getElementById('userfom:panelID:checkboxDT:'+i+':inputuser').value;
		if(usernamei == null || usernamei == ""){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':inputuser').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':usererror').innerHTML = "Please Enter User Name";
		}else{
			if(usernamei !=null)
				{
				if (namevalidation.test(usernamei)) {
					/*usercheck([{name:'param1', value:usernamei },{name:'param2' , value:'userfom:panelID:checkboxDT:'+i+':inputuser'},{name:'param3' , value:'userfom:panelID:checkboxDT:'+i+':usererror'}]);*/
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputuser').style.borderColor = "green";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':usererror').innerHTML = "";
				}else{
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputuser').style.borderColor = "red";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':usererror').innerHTML = "User Name Atleast 3 charactor";
				}
				}
		}
		
	
}
}
/*
function usersucess(){
	alert("success");
	var obj=document.getElementById('userfom:checktest').value;
	var obj1=document.getElementById('userfom:checkerror').value;
	alert("id 1- "+obj);
	document.getElementById(obj).style.borderColor = "green";
	document.getElementById(obj1).innerHTML = "";
	
}

function userfail(){
	alert("fail");
	var obj=document.getElementById('userfom:checktest').value;
	var obj1=document.getElementById('userfom:checkerror').value;
	alert("id - "+obj);
	document.getElementById(obj).style.borderColor = "red";
	document.getElementById(obj1).innerHTML = "User Name Already Exist";
}*/

function passwordcheck(){
	var table = document.getElementById('userfom:panelID:checkboxDT_data');
	var namevalidation = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	var row;
	for (var i = 0; row = table.rows[i]; i++) {
		var usernamei=document.getElementById('userfom:panelID:checkboxDT:'+i+':inputpwd').value;
		if(usernamei == null || usernamei == ""){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':inputpwd').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':userpwd').innerHTML = "Please Enter the Password";
		}else{
			if(usernamei !=null)
				{
				if (namevalidation.test(usernamei)) {
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputpwd').style.borderColor = "green";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userpwd').innerHTML = "";
				}else{
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputpwd').style.borderColor = "red";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userpwd').innerHTML = "Minimum 6 Letters (eg:aa@111a) ";
				}
				
				}
			
		}
		
	
}
}

function emailcheck(){
	var table = document.getElementById('userfom:panelID:checkboxDT_data');
	/*var emailvalidation = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;*/
	var emailvalidation =/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
	var row;
	for (var i = 0; row = table.rows[i]; i++) {
		var usernamei=document.getElementById('userfom:panelID:checkboxDT:'+i+':inputmail').value;
		if(usernamei == null || usernamei == ""){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':inputmail').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':usermail').innerHTML = "Please Enter the Email ID";
		}else{
			if(usernamei != null)
				{
				if(emailvalidation.test(usernamei))
					{
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputmail').style.borderColor = "green";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':usermail').innerHTML = "";
					}else{
						document.getElementById('userfom:panelID:checkboxDT:'+i+':inputmail').style.borderColor = "red";
						document.getElementById('userfom:panelID:checkboxDT:'+i+':usermail').innerHTML = "Please Enter the Valid Email ID(Eg:aa@bb.cc)";
					}
			
				}
		}
		
	
}
}

function phonecheck(){
	var table = document.getElementById('userfom:panelID:checkboxDT_data');
	var phonevalidation = /^[0-9]{10,12}$/;
	var row;
	for (var i = 0; row = table.rows[i]; i++) {
		var usernamei=document.getElementById('userfom:panelID:checkboxDT:'+i+':inputphone').value;
		if(usernamei == null || usernamei == ""){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':inputphone').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':userphone').innerHTML = "Please Enter the Phone Number";
		}else{
			if(usernamei != null){
				if(phonevalidation.test(usernamei)){
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputphone').style.borderColor = "green";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userphone').innerHTML = "";
				}else{
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputphone').style.borderColor = "red";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userphone').innerHTML = "Phone Number Should be 10 digit Number";
				}
			}
			
		}
}
}
function usertypecheck(){
	alert("usertype");
	var table = document.getElementById('userfom:panelID:checkboxDT_data');
	var row;
	for (var i = 0; row = table.rows[i]; i++) {
		var usertypei=document.getElementById('userfom:panelID:checkboxDT:'+i+':utype').value;
		if(usertypei == "select"){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':utype').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':usertype').innerHTML = "Please Choose the User Type";
		}else{
			document.getElementById('userfom:panelID:checkboxDT:'+i+':utype').style.borderColor = "green";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':usertype').innerHTML = "";
		}
}
}

/*function validation(){
	var table = document.getElementById('userfom:panelID:checkboxDT_data');
	var namevalidation = /^[a-zA-Z ]{3,30}$/;
	var inputpwdvalidation = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	var emailvalidation = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	var phonevalidation = /^[0-9]{10,12}$/;
	var row;
	for (var i = 0; row = table.rows[i]; i++) {
		var count=0;
		var usernamei=document.getElementById('userfom:panelID:checkboxDT:'+i+':inputuser').value;
		if(usernamei == null || usernamei == ""){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':inputuser').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':usererror').innerHTML = "Please Enter User Name";
		}else{
			if(usernamei !=null)
				{
				if (namevalidation.test(usernamei)) {
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputuser').style.borderColor = "green";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':usererror').innerHTML = "";
					count++;
				}else{
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputuser').style.borderColor = "red";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':usererror').innerHTML = "User Name Atlease 3 charector";
				}
				}
		}
		var passwordi=document.getElementById('userfom:panelID:checkboxDT:'+i+':inputinputpwd').value;
		if(passwordi == null || passwordi == ""){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':inputinputpwd').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':userinputpwd').innerHTML = "Please Enter the Password";
		}else{
			if(passwordi !=null)
				{
				if (inputpwdvalidation.test(passwordi)) {
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputinputpwd').style.borderColor = "green";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userinputpwd').innerHTML = "";
					count++;
				}else{
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputinputpwd').style.borderColor = "red";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userinputpwd').innerHTML = "Minimum 6 Letters (eg:aa@111a) ";
				}
				
				}			
		}
		var emaili=document.getElementById('userfom:panelID:checkboxDT:'+i+':inputusermail').value;
		if(emaili == null || emaili == ""){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':inputusermail').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':userusermail').innerHTML = "Please Enter the Email ID";
		}else{
			if(emaili != null)
				{
				if(emailvalidation.test(emaili))
					{
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputusermail').style.borderColor = "green";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userusermail').innerHTML = "";
					count++;
					}else{
						document.getElementById('userfom:panelID:checkboxDT:'+i+':inputusermail').style.borderColor = "red";
						document.getElementById('userfom:panelID:checkboxDT:'+i+':userusermail').innerHTML = "Please Enter the Valid Email ID(Eg:aa@bb.cc)";
					}
			
				}
		}
		var phonenumberi=document.getElementById('userfom:panelID:checkboxDT:'+i+':inputphone').value;
		if(phonenumberi == null || phonenumberi == ""){
			document.getElementById('userfom:panelID:checkboxDT:'+i+':inputphone').style.borderColor = "red";
			document.getElementById('userfom:panelID:checkboxDT:'+i+':userphone').innerHTML = "Please Enter the Phone Number";
		}else{
			if(phonenumberi != null){
				if(phonevalidation.test(phonenumberi)){
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputphone').style.borderColor = "green";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userphone').innerHTML = "";
					count++;
				}else{
					document.getElementById('userfom:panelID:checkboxDT:'+i+':inputphone').style.borderColor = "red";
					document.getElementById('userfom:panelID:checkboxDT:'+i+':userphone').innerHTML = "Phone Number Should be 10 digit Number";
				}
			}
			
		}		
		if(count==4){
			alert("count "+count);
			document.getElementById('userfom:panelID:confirmID').click();	
		}
		else{
			alert("count 1"+count);
		}
}
}
*/

function confirm()
{
	var namevalidation = /^[a-zA-Z ]{3,30}$/;
	var passwordv = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	var emailv= /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
	var phonev = /^[0-9]{10,12}$/;
	var row1=false;var row2=false;var row3=false;var row4=false;var row5=false;
	var i=0;var c=0;var c1=0;;var c2=0;var c3=0;
	/*1st row*/
	var status=false;var statuspass=false;var statususermail=false;var statusphn=false;	
	var input=document.getElementById('userfom:panelID:checkboxDT:0:inputuser').value;
	if(input !=null){
		status=true;
	}
	var input1=document.getElementById('userfom:panelID:checkboxDT:0:inputpwd').value;
	if(input1 !=null){
		statuspass=true;
		}
	var input2=document.getElementById('userfom:panelID:checkboxDT:0:inputmail').value;
	if(input2 !=null){
		statususermail=true;
		}
	var input3=document.getElementById('userfom:panelID:checkboxDT:0:inputphone').value;
	if(input3 !=null){
		statusphn=true;	
		}
	if(status==true || statuspass==true || statususermail==true || statusphn==true){
		if (input == null || input == "") {
	 		document.getElementById('userfom:panelID:checkboxDT:0:inputuser').style.borderColor = "red";
	 		document.getElementById('userfom:panelID:checkboxDT:0:usererror').innerHTML="Please Enter User Name";
	 		row1=false;
	 	 }else{
	 		if (input != null) {
				{
					if (namevalidation.test(input)) {
						document.getElementById('userfom:panelID:checkboxDT:0:inputuser').style.borderColor = "green";
				  		document.getElementById('userfom:panelID:checkboxDT:0:usererror').innerHTML="";
				  		status=true;row1=true;i++;
					
					} else {
						document.getElementById('userfom:panelID:checkboxDT:0:inputuser').style.borderColor = "red";
				 		document.getElementById('userfom:panelID:checkboxDT:0:usererror').innerHTML="User Name Atleast 3 charactor";
				 		row1=false;
					}
				}
	 		}
	 	 }
		if (input1 == null || input1 == "") {
	 		document.getElementById('userfom:panelID:checkboxDT:0:inputpwd').style.borderColor = "red";
	 		document.getElementById('userfom:panelID:checkboxDT:0:userpwd').innerHTML="Please Enter Password";
	 		row1=false;
	 	 }else
	 	 {
	 		if (input1 != null) {
				{
					if (passwordv.test(input1)) {
							document.getElementById('userfom:panelID:checkboxDT:0:inputpwd').style.borderColor = "green";
					  		document.getElementById('userfom:panelID:checkboxDT:0:userpwd').innerHTML="";	
					  		statuspass=true;
					  		row1=true;i++;
					}
					else
					{
						document.getElementById('userfom:panelID:checkboxDT:0:inputpwd').style.borderColor = "red";
				 		document.getElementById('userfom:panelID:checkboxDT:0:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
				 		row1=false;
					}
				}
			}	  		
	 	 } 
		if (input2 == null || input2 == "") {
	 		document.getElementById('userfom:panelID:checkboxDT:0:inputmail').style.borderColor = "red";
	 		document.getElementById('userfom:panelID:checkboxDT:0:usermail').innerHTML="Please Enter Email ID";
	 		row1=false;
	 	 }else{
	 		if (input2 != null) {
				{
					if (emailv.test(input2)) {
						document.getElementById('userfom:panelID:checkboxDT:0:inputmail').style.borderColor = "green";
				  		document.getElementById('userfom:panelID:checkboxDT:0:usermail').innerHTML="";
				  		statususermail=true;row1=true;i++;
					}
					else {
						document.getElementById('userfom:panelID:checkboxDT:0:inputmail').style.borderColor = "red";
				 		document.getElementById('userfom:panelID:checkboxDT:0:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
				 		row1=false;
					}
				}
			}	  		
	 	 } 
			if (input3 == null || input3 == "") {
		 		document.getElementById('userfom:panelID:checkboxDT:0:inputphone').style.borderColor = "red";
		 		document.getElementById('userfom:panelID:checkboxDT:0:userphone').innerHTML="Please Enter Phone Number";
		 		row1=false;
		 	 }
			 else{
		 		if (input3 != null) {
					{
						if (phonev.test(input3)) {
							document.getElementById('userfom:panelID:checkboxDT:0:inputphone').style.borderColor = "green";
					  		document.getElementById('userfom:panelID:checkboxDT:0:userphone').innerHTML="";
					  		statusphn=true;row1=true;i++;
						}
						else {
							document.getElementById('userfom:panelID:checkboxDT:0:inputphone').style.borderColor = "red";
					 		document.getElementById('userfom:panelID:checkboxDT:0:userphone').innerHTML="Phone Number Should be 10 digit Number";
					 		row1=false;
							}
						}
					}	  		
		 	 	} 
	}
	if(i==0 || i==4){
		if(row1==true){
			var status1=false;var statuspass1=false;var statususermail1=false;var statusphn1=false;
			
			try{
				var input11=document.getElementById('userfom:panelID:checkboxDT:1:inputuser').value;
				var input12=document.getElementById('userfom:panelID:checkboxDT:1:inputpwd').value;
				var input13=document.getElementById('userfom:panelID:checkboxDT:1:inputmail').value;
				var input14=document.getElementById('userfom:panelID:checkboxDT:1:inputphone').value;
				if(input11 !=null || input11 != ""){
					status1=true;
				}			
				if(input12 !=null || input12 != ""){
					statuspass1=true;
					}			
				if(input13 !=null || input13 != ""){
					statususermail1=true;
					}			
				if(input14 !=null || input14 != ""){
					statusphn1=true;	
					}
				if(status1==true || statuspass1==true || statususermail1==true || statusphn1==true){
				if (input11 == null || input11 == "") {
				 		document.getElementById('userfom:panelID:checkboxDT:1:inputuser').style.borderColor = "red";
				 		document.getElementById('userfom:panelID:checkboxDT:1:usererror').innerHTML="Please Enter User Name";
				 		row2=false;
				 	 }else{
				 		if (input11 != null) {
							{
								if (namevalidation.test(input11)) {
									document.getElementById('userfom:panelID:checkboxDT:1:inputuser').style.borderColor = "green";
							  		document.getElementById('userfom:panelID:checkboxDT:1:usererror').innerHTML="";
							  		status1=true;row2=true;c++;
								
								} else {
									document.getElementById('userfom:panelID:checkboxDT:1:inputuser').style.borderColor = "red";
							 		document.getElementById('userfom:panelID:checkboxDT:1:usererror').innerHTML="User Name Atleast 3 charactor";
							 		row2=true;
								}
							}
				 		}
				 	 }
					if (input12 == null || input12 == "") {
				 		document.getElementById('userfom:panelID:checkboxDT:1:inputpwd').style.borderColor = "red";
				 		document.getElementById('userfom:panelID:checkboxDT:1:userpwd').innerHTML="Please Enter Password";
				 		row2=false;
				 	 }else
				 	 {
				 		if (input12 != null) {
							{
								if (passwordv.test(input12)) {
										document.getElementById('userfom:panelID:checkboxDT:1:inputpwd').style.borderColor = "green";
								  		document.getElementById('userfom:panelID:checkboxDT:1:userpwd').innerHTML="";	
								  		statuspass1=true;row2=true;c++;
								}
								else
								{
									document.getElementById('userfom:panelID:checkboxDT:1:inputpwd').style.borderColor = "red";
							 		document.getElementById('userfom:panelID:checkboxDT:1:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
							 		row2=false;
								}
							}
						}	  		
				 	 } 
					if (input13 == null || input13 == "") {
				 		document.getElementById('userfom:panelID:checkboxDT:1:inputmail').style.borderColor = "red";
				 		document.getElementById('userfom:panelID:checkboxDT:1:usermail').innerHTML="Please Enter Email ID";
				 		row2=false;
				 	 }else{
				 		if (input13 != null) {
							{
								if (emailv.test(input13)) {
									document.getElementById('userfom:panelID:checkboxDT:1:inputmail').style.borderColor = "green";
							  		document.getElementById('userfom:panelID:checkboxDT:1:usermail').innerHTML="";
							  		statususermail1=true;row2=true;c++;
								}
								else {
									document.getElementById('userfom:panelID:checkboxDT:1:inputmail').style.borderColor = "red";
							 		document.getElementById('userfom:panelID:checkboxDT:1:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
							 		row2=false;
								}
							}
						}	  		
				 	 } 
						if (input14 == null || input14 == "") {
					 		document.getElementById('userfom:panelID:checkboxDT:1:inputphone').style.borderColor = "red";
					 		document.getElementById('userfom:panelID:checkboxDT:1:userphone').innerHTML="Please Enter Phone Number";
					 		row2=false;
					 	 }
						 else{
					 		if (input14 != null) {
								{
									if (phonev.test(input14)) {
										document.getElementById('userfom:panelID:checkboxDT:1:inputphone').style.borderColor = "green";
								  		document.getElementById('userfom:panelID:checkboxDT:1:userphone').innerHTML="";
								  		statusphn1=true;row2=true;c++;
									}
									else {
										document.getElementById('userfom:panelID:checkboxDT:1:inputphone').style.borderColor = "red";
								 		document.getElementById('userfom:panelID:checkboxDT:1:userphone').innerHTML="Phone Number Should be 10 digit Number";
								 		row2=false;
										}
									}
								}	  		
					 	 	} 
				}
				if(c==4){
					if(row2==true){
						/*3 rd row*/
						try{
							var status2=false;var statuspass2=false;var statususermail2=false;var statusphn2=false;			
							var input21=document.getElementById('userfom:panelID:checkboxDT:2:inputuser').value;
							var input22=document.getElementById('userfom:panelID:checkboxDT:2:inputpwd').value;
							var input23=document.getElementById('userfom:panelID:checkboxDT:2:inputmail').value;
							var input24=document.getElementById('userfom:panelID:checkboxDT:2:inputphone').value;
							if(input21 !=null){
								status2=true;
							}						
							if(input22 !=null){
								statuspass2=true;
								}						
							if(input23 !=null){
								statususermail2=true;
								}						
							if(input24 !=null){
								statusphn2=true;	
								}
							if(status2==true || statuspass2==true || statususermail2==true || statusphn2==true){
								if (input21 == null || input21 == "") {
							 		document.getElementById('userfom:panelID:checkboxDT:2:inputuser').style.borderColor = "red";
							 		document.getElementById('userfom:panelID:checkboxDT:2:usererror').innerHTML="Please Enter User Name";
							 		row3=false;
							 	 }else{
							 		if (input21 != null) {
										{
											if (namevalidation.test(input21)) {
												document.getElementById('userfom:panelID:checkboxDT:2:inputuser').style.borderColor = "green";
										  		document.getElementById('userfom:panelID:checkboxDT:2:usererror').innerHTML="";
										  		status2=true;row3=true;c1++;
											
											} else {
												document.getElementById('userfom:panelID:checkboxDT:2:inputuser').style.borderColor = "red";
										 		document.getElementById('userfom:panelID:checkboxDT:2:usererror').innerHTML="User Name Atleast 3 charactor";
										 		row3=true;
											}
										}
							 		}
							 	 }
								if (input22 == null || input22 == "") {
							 		document.getElementById('userfom:panelID:checkboxDT:2:inputpwd').style.borderColor = "red";
							 		document.getElementById('userfom:panelID:checkboxDT:2:userpwd').innerHTML="Please Enter Password";
							 		row3=false;
							 	 }else
							 	 {
							 		if (input22 != null) {
										{
											if (passwordv.test(input22)) {
													document.getElementById('userfom:panelID:checkboxDT:2:inputpwd').style.borderColor = "green";
											  		document.getElementById('userfom:panelID:checkboxDT:2:userpwd').innerHTML="";	
											  		statuspass2=true;row3=true;c1++;
											}
											else
											{
												document.getElementById('userfom:panelID:checkboxDT:2:inputpwd').style.borderColor = "red";
										 		document.getElementById('userfom:panelID:checkboxDT:2:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
										 		row3=false;
											}
										}
									}	  		
							 	 } 
								if (input23 == null || input23 == "") {
							 		document.getElementById('userfom:panelID:checkboxDT:2:inputmail').style.borderColor = "red";
							 		document.getElementById('userfom:panelID:checkboxDT:2:usermail').innerHTML="Please Enter Email ID";
							 		row3=false;
							 	 }else{
							 		if (input23 != null) {
										{
											if (emailv.test(input23)) {
												document.getElementById('userfom:panelID:checkboxDT:2:inputmail').style.borderColor = "green";
										  		document.getElementById('userfom:panelID:checkboxDT:2:usermail').innerHTML="";
										  		statususermail2=true;row3=true;c1++;
											}
											else {
												document.getElementById('userfom:panelID:checkboxDT:2:inputmail').style.borderColor = "red";
										 		document.getElementById('userfom:panelID:checkboxDT:2:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
										 		row3=false;
											}
										}
									}	  		
							 	 } 
									if (input24 == null || input24 == "") {
								 		document.getElementById('userfom:panelID:checkboxDT:2:inputphone').style.borderColor = "red";
								 		document.getElementById('userfom:panelID:checkboxDT:2:userphone').innerHTML="Please Enter Phone Number";
								 		row3=false;
								 	 }
									 else{
								 		if (input24 != null) {
											{
												if (phonev.test(input24)) {
													document.getElementById('userfom:panelID:checkboxDT:2:inputphone').style.borderColor = "green";
											  		document.getElementById('userfom:panelID:checkboxDT:2:userphone').innerHTML="";
											  		statusphn2=true;row3=true;c1++;
												}
												else {
													document.getElementById('userfom:panelID:checkboxDT:2:inputphone').style.borderColor = "red";
											 		document.getElementById('userfom:panelID:checkboxDT:2:userphone').innerHTML="Phone Number Should be 10 digit Number";
											 		row3=false;
													}
												}
											}	  		
								 	 	}
							}
							if(c1==4){
								if(row3==true){
									/*3 rd row*/
									try{										
										var status3=false;var statuspass3=false;var statususermail3=false;var statusphn3=false;			
										var input31=document.getElementById('userfom:panelID:checkboxDT:3:inputuser').value;
										
										if(input31 !=null){
											status3=true;
										}
										var input32=document.getElementById('userfom:panelID:checkboxDT:3:inputpwd').value;
										if(input32 !=null){
											statuspass3=true;
											}
										var input33=document.getElementById('userfom:panelID:checkboxDT:3:inputmail').value;
										if(input33 !=null){
											statususermail3=true;
											}
										var input34=document.getElementById('userfom:panelID:checkboxDT:3:inputphone').value;
										if(input34 !=null){
											statusphn3=true;	
											}
										if(status3==true || statuspass3==true || statususermail3==true || statusphn3==true){
											if (input31 == null || input31 == "") {
										 		document.getElementById('userfom:panelID:checkboxDT:3:inputuser').style.borderColor = "red";
										 		document.getElementById('userfom:panelID:checkboxDT:3:usererror').innerHTML="Please Enter User Name";
										 		row4=false;
										 	 }else{
										 		if (input31 != null) {
													{
														if (namevalidation.test(input31)) {
															document.getElementById('userfom:panelID:checkboxDT:3:inputuser').style.borderColor = "green";
													  		document.getElementById('userfom:panelID:checkboxDT:3:usererror').innerHTML="";
													  		status3=true;row4=true;c2++;
														
														} else {
															document.getElementById('userfom:panelID:checkboxDT:3:inputuser').style.borderColor = "red";
													 		document.getElementById('userfom:panelID:checkboxDT:3:usererror').innerHTML="User Name Atleast 3 charactor";
													 		row4=true;
														}
													}
										 		}
										 	 }
											if (input32 == null || input32 == "") {
										 		document.getElementById('userfom:panelID:checkboxDT:3:inputpwd').style.borderColor = "red";
										 		document.getElementById('userfom:panelID:checkboxDT:3:userpwd').innerHTML="Please Enter Password";
										 		row4=false;
										 	 }else
										 	 {
										 		if (input32 != null) {
													{
														if (passwordv.test(input32)) {
																document.getElementById('userfom:panelID:checkboxDT:3:inputpwd').style.borderColor = "green";
														  		document.getElementById('userfom:panelID:checkboxDT:3:userpwd').innerHTML="";	
														  		statuspass3=true;row4=true;c2++;
														}
														else
														{
															document.getElementById('userfom:panelID:checkboxDT:3:inputpwd').style.borderColor = "red";
													 		document.getElementById('userfom:panelID:checkboxDT:3:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
													 		row4=false;
														}
													}
												}	  		
										 	 } 
											if (input33 == null || input33 == "") {
										 		document.getElementById('userfom:panelID:checkboxDT:3:inputmail').style.borderColor = "red";
										 		document.getElementById('userfom:panelID:checkboxDT:3:usermail').innerHTML="Please Enter Email ID";
										 		row4=false;
										 	 }else{
										 		if (input33 != null) {
													{
														if (emailv.test(input33)) {
															document.getElementById('userfom:panelID:checkboxDT:3:inputmail').style.borderColor = "green";
													  		document.getElementById('userfom:panelID:checkboxDT:3:usermail').innerHTML="";
													  		statususermail3=true;row4=true;c2++;
														}
														else {
															document.getElementById('userfom:panelID:checkboxDT:3:inputmail').style.borderColor = "red";
													 		document.getElementById('userfom:panelID:checkboxDT:3:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
													 		row4=false;
														}
													}
												}	  		
										 	 } 
												if (input34 == null || input34 == "") {
											 		document.getElementById('userfom:panelID:checkboxDT:3:inputphone').style.borderColor = "red";
											 		document.getElementById('userfom:panelID:checkboxDT:3:userphone').innerHTML="Please Enter Phone Number";
											 		row4=false;
											 	 }
												 else{
											 		if (input34 != null) {
														{
															if (phonev.test(input34)) {
																document.getElementById('userfom:panelID:checkboxDT:3:inputphone').style.borderColor = "green";
														  		document.getElementById('userfom:panelID:checkboxDT:3:userphone').innerHTML="";
														  		statusphn3=true;row4=true;c2++;
															}
															else {
																document.getElementById('userfom:panelID:checkboxDT:3:inputphone').style.borderColor = "red";
														 		document.getElementById('userfom:panelID:checkboxDT:3:userphone').innerHTML="Phone Number Should be 10 digit Number";
														 		row4=false;
																}
															}
														}	  		
											 	 	} 
										}
										if(c2==4){
											if(row4==true){
												
												/*4 th row*/
												try{
													var status4=false;var statuspass4=false;var statususermail4=false;var statusphn4=false;			
													var input41=document.getElementById('userfom:panelID:checkboxDT:4:inputuser').value;
													
													if(input41 !=null){
														status4=true;
													}
													var input42=document.getElementById('userfom:panelID:checkboxDT:4:inputpwd').value;
													if(input42 !=null){
														statuspass4=true;
														}
													var input43=document.getElementById('userfom:panelID:checkboxDT:4:inputmail').value;
													if(input43 !=null){
														statususermail4=true;
														}
													var input44=document.getElementById('userfom:panelID:checkboxDT:4:inputphone').value;
													if(input44 !=null){
														statusphn4=true;	
														}
													if(status4==true || statuspass4==true || statususermail4==true || statusphn4==true){
														if (input41 == null || input41 == "") {
													 		document.getElementById('userfom:panelID:checkboxDT:4:inputuser').style.borderColor = "red";
													 		document.getElementById('userfom:panelID:checkboxDT:4:usererror').innerHTML="Please Enter User Name";
													 		row5=false;
													 	 }else{
													 		if (input41 != null) {
																{
																	if (namevalidation.test(input41)) {
																		document.getElementById('userfom:panelID:checkboxDT:4:inputuser').style.borderColor = "green";
																  		document.getElementById('userfom:panelID:checkboxDT:4:usererror').innerHTML="";
																  		status4=true;row5=true;c3++;
																	
																	} else {
																		document.getElementById('userfom:panelID:checkboxDT:4:inputuser').style.borderColor = "red";
																 		document.getElementById('userfom:panelID:checkboxDT:4:usererror').innerHTML="User Name Atleast 3 charactor";
																 		row5=true;
																	}
																}
													 		}
													 	 }
														if (input42 == null || input42 == "") {
													 		document.getElementById('userfom:panelID:checkboxDT:4:inputpwd').style.borderColor = "red";
													 		document.getElementById('userfom:panelID:checkboxDT:4:userpwd').innerHTML="Please Enter Password";
													 		row5=false;
													 	 }else
													 	 {
													 		if (input42 != null) {
																{
																	if (passwordv.test(input42)) {
																			document.getElementById('userfom:panelID:checkboxDT:4:inputpwd').style.borderColor = "green";
																	  		document.getElementById('userfom:panelID:checkboxDT:4:userpwd').innerHTML="";	
																	  		statuspass4=true;row5=true;c3++;
																	}
																	else
																	{
																		document.getElementById('userfom:panelID:checkboxDT:4:inputpwd').style.borderColor = "red";
																 		document.getElementById('userfom:panelID:checkboxDT:4:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
																 		row5=false;
																	}
																}
															}	  		
													 	 } 
														if (input43 == null || input43 == "") {
													 		document.getElementById('userfom:panelID:checkboxDT:4:inputmail').style.borderColor = "red";
													 		document.getElementById('userfom:panelID:checkboxDT:4:usermail').innerHTML="Please Enter Email ID";
													 		row5=false;
													 	 }else{
													 		if (input43 != null) {
																{
																	if (emailv.test(input43)) {
																		document.getElementById('userfom:panelID:checkboxDT:4:inputmail').style.borderColor = "green";
																  		document.getElementById('userfom:panelID:checkboxDT:4:usermail').innerHTML="";
																  		statususermail4=true;row5=true;c3++;
																	}
																	else {
																		document.getElementById('userfom:panelID:checkboxDT:4:inputmail').style.borderColor = "red";
																 		document.getElementById('userfom:panelID:checkboxDT:4:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
																 		row5=false;
																	}
																}
															}	  		
													 	 } 
															if (input44 == null || input44 == "") {
														 		document.getElementById('userfom:panelID:checkboxDT:4:inputphone').style.borderColor = "red";
														 		document.getElementById('userfom:panelID:checkboxDT:4:userphone').innerHTML="Please Enter Phone Number";
														 		row5=false;
														 	 }
															 else{
														 		if (input44 != null) {
																	{
																		if (phonev.test(input44)) {
																			document.getElementById('userfom:panelID:checkboxDT:4:inputphone').style.borderColor = "green";
																	  		document.getElementById('userfom:panelID:checkboxDT:4:userphone').innerHTML="";
																	  		statusphn4=true;row5=true;c3++;
																		}
																		else {
																			document.getElementById('userfom:panelID:checkboxDT:4:inputphone').style.borderColor = "red";
																	 		document.getElementById('userfom:panelID:checkboxDT:4:userphone').innerHTML="Phone Number Should be 10 digit Number";
																	 		row5=false;
																			}
																		}
																	}	  		
														 	 	}
													}
													if(c3==4 || c3==0){
														document.getElementById('userfom:panelID:confirmID').click();	
													}
												}
												catch(e2){
													document.getElementById('userfom:panelID:confirmID').click();	
												}
												
												}
										}
										else if(c2==0){
											document.getElementById('userfom:panelID:confirmID').click();	
										}
										
									}
									catch(e1){
										document.getElementById('userfom:panelID:confirmID').click();	
									}
								}
							}
							else if(c1==0){
								document.getElementById('userfom:panelID:confirmID').click();	
							}
						}
						catch(ee){
							document.getElementById('userfom:panelID:confirmID').click();	
						}
						
						}					
				}
				else if(c==0){
					document.getElementById('userfom:panelID:confirmID').click();	
				}
			}
			catch(e){
				document.getElementById('userfom:panelID:confirmID').click();	
			}
		}
	}
	/*var table = document.getElementById('userfom:panelID:checkboxDT_data');
	var row;var tic=0;
	for (var i = 0; row = table.rows[i]; i++) {
		var checkb=document.getElementById('userfom:panelID:checkboxDT:'+i+':checkbox').value;
		alert("check - "+checkb);
		if(checkb == null || checkb == ""){
		}
		else{
			tic ++;
		}
	}
	alert("tick "+tic);
	if(tic == 0){
		for (var i = 0; row = table.rows[i]; i++) {
			document.getElementById('userfom:panelID:checkboxDT:'+i+':checkbox').style.borderColor = "red";
	 		document.getElementById('userfom:panelID:checkboxDT:'+i+':usercheck').innerHTML="Please Select Rows";
	 		alert("check z");
		}		
	}*/
}

function addrowvalidate()
{
	var namevalidation = /^[a-zA-Z ]{3,30}$/;
	var passwordv = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	var emailv= /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
	var phonev = /^[0-9]{10,12}$/;
	var row1=false;var row2=false;var row3=false;var row4=false;var row5=false;
	var i=0;var c=0;var c1=0;;var c2=0;var c3=0;
	/*1st row*/
	var status=false;var statuspass=false;var statususermail=false;var statusphn=false;	
	try{
		var input=document.getElementById('userfom:panelID:checkboxDT:0:inputuser').value;
		if(input !=null){
			status=true;
		}
		var input1=document.getElementById('userfom:panelID:checkboxDT:0:inputpwd').value;
		if(input1 !=null){
			statuspass=true;
			}
		var input2=document.getElementById('userfom:panelID:checkboxDT:0:inputmail').value;
		if(input2 !=null){
			statususermail=true;
			}
		var input3=document.getElementById('userfom:panelID:checkboxDT:0:inputphone').value;
		if(input3 !=null){
			statusphn=true;	
			}
		if(status==true || statuspass==true || statususermail==true || statusphn==true){
			if (input == null || input == "") {
		 		document.getElementById('userfom:panelID:checkboxDT:0:inputuser').style.borderColor = "red";
		 		document.getElementById('userfom:panelID:checkboxDT:0:usererror').innerHTML="Please Enter User Name";
		 		row1=false;
		 	 }else{
		 		if (input != null) {
					{
						if (namevalidation.test(input)) {
							document.getElementById('userfom:panelID:checkboxDT:0:inputuser').style.borderColor = "green";
					  		document.getElementById('userfom:panelID:checkboxDT:0:usererror').innerHTML="";
					  		status=true;row1=true;i++;
						
						} else {
							document.getElementById('userfom:panelID:checkboxDT:0:inputuser').style.borderColor = "red";
					 		document.getElementById('userfom:panelID:checkboxDT:0:usererror').innerHTML="User Name Atleast 3 charactor";
					 		row1=false;
						}
					}
		 		}
		 	 }
			if (input1 == null || input1 == "") {
		 		document.getElementById('userfom:panelID:checkboxDT:0:inputpwd').style.borderColor = "red";
		 		document.getElementById('userfom:panelID:checkboxDT:0:userpwd').innerHTML="Please Enter Password";
		 		row1=false;
		 	 }else
		 	 {
		 		if (input1 != null) {
					{
						if (passwordv.test(input1)) {
								document.getElementById('userfom:panelID:checkboxDT:0:inputpwd').style.borderColor = "green";
						  		document.getElementById('userfom:panelID:checkboxDT:0:userpwd').innerHTML="";	
						  		statuspass=true;
						  		row1=true;i++;
						}
						else
						{
							document.getElementById('userfom:panelID:checkboxDT:0:inputpwd').style.borderColor = "red";
					 		document.getElementById('userfom:panelID:checkboxDT:0:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
					 		row1=false;
						}
					}
				}	  		
		 	 } 
			if (input2 == null || input2 == "") {
		 		document.getElementById('userfom:panelID:checkboxDT:0:inputmail').style.borderColor = "red";
		 		document.getElementById('userfom:panelID:checkboxDT:0:usermail').innerHTML="Please Enter Email ID";
		 		row1=false;
		 	 }else{
		 		if (input2 != null) {
					{
						if (emailv.test(input2)) {
							document.getElementById('userfom:panelID:checkboxDT:0:inputmail').style.borderColor = "green";
					  		document.getElementById('userfom:panelID:checkboxDT:0:usermail').innerHTML="";
					  		statususermail=true;row1=true;i++;
						}
						else {
							document.getElementById('userfom:panelID:checkboxDT:0:inputmail').style.borderColor = "red";
					 		document.getElementById('userfom:panelID:checkboxDT:0:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
					 		row1=false;
						}
					}
				}	  		
		 	 } 
				if (input3 == null || input3 == "") {
			 		document.getElementById('userfom:panelID:checkboxDT:0:inputphone').style.borderColor = "red";
			 		document.getElementById('userfom:panelID:checkboxDT:0:userphone').innerHTML="Please Enter Phone Number";
			 		row1=false;
			 	 }
				 else{
			 		if (input3 != null) {
						{
							if (phonev.test(input3)) {
								document.getElementById('userfom:panelID:checkboxDT:0:inputphone').style.borderColor = "green";
						  		document.getElementById('userfom:panelID:checkboxDT:0:userphone').innerHTML="";
						  		statusphn=true;row1=true;i++;
							}
							else {
								document.getElementById('userfom:panelID:checkboxDT:0:inputphone').style.borderColor = "red";
						 		document.getElementById('userfom:panelID:checkboxDT:0:userphone').innerHTML="Phone Number Should be 10 digit Number";
						 		row1=false;
								}
							}
						}	  		
			 	 	} 
		}
		if(i==0 || i==4){
			if(row1==true){
				var status1=false;var statuspass1=false;var statususermail1=false;var statusphn1=false;
				
				try{
					var input11=document.getElementById('userfom:panelID:checkboxDT:1:inputuser').value;
					var input12=document.getElementById('userfom:panelID:checkboxDT:1:inputpwd').value;
					var input13=document.getElementById('userfom:panelID:checkboxDT:1:inputmail').value;
					var input14=document.getElementById('userfom:panelID:checkboxDT:1:inputphone').value;
					if(input11 !=null || input11 != ""){
						status1=true;
					}			
					if(input12 !=null || input12 != ""){
						statuspass1=true;
						}			
					if(input13 !=null || input13 != ""){
						statususermail1=true;
						}			
					if(input14 !=null || input14 != ""){
						statusphn1=true;	
						}
					if(status1==true || statuspass1==true || statususermail1==true || statusphn1==true){
					if (input11 == null || input11 == "") {
					 		document.getElementById('userfom:panelID:checkboxDT:1:inputuser').style.borderColor = "red";
					 		document.getElementById('userfom:panelID:checkboxDT:1:usererror').innerHTML="Please Enter User Name";
					 		row2=false;
					 	 }else{
					 		if (input11 != null) {
								{
									if (namevalidation.test(input11)) {
										document.getElementById('userfom:panelID:checkboxDT:1:inputuser').style.borderColor = "green";
								  		document.getElementById('userfom:panelID:checkboxDT:1:usererror').innerHTML="";
								  		status1=true;row2=true;c++;
									
									} else {
										document.getElementById('userfom:panelID:checkboxDT:1:inputuser').style.borderColor = "red";
								 		document.getElementById('userfom:panelID:checkboxDT:1:usererror').innerHTML="User Name Atleast 3 charactor";
								 		row2=true;
									}
								}
					 		}
					 	 }
						if (input12 == null || input12 == "") {
					 		document.getElementById('userfom:panelID:checkboxDT:1:inputpwd').style.borderColor = "red";
					 		document.getElementById('userfom:panelID:checkboxDT:1:userpwd').innerHTML="Please Enter Password";
					 		row2=false;
					 	 }else
					 	 {
					 		if (input12 != null) {
								{
									if (passwordv.test(input12)) {
											document.getElementById('userfom:panelID:checkboxDT:1:inputpwd').style.borderColor = "green";
									  		document.getElementById('userfom:panelID:checkboxDT:1:userpwd').innerHTML="";	
									  		statuspass1=true;row2=true;c++;
									}
									else
									{
										document.getElementById('userfom:panelID:checkboxDT:1:inputpwd').style.borderColor = "red";
								 		document.getElementById('userfom:panelID:checkboxDT:1:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
								 		row2=false;
									}
								}
							}	  		
					 	 } 
						if (input13 == null || input13 == "") {
					 		document.getElementById('userfom:panelID:checkboxDT:1:inputmail').style.borderColor = "red";
					 		document.getElementById('userfom:panelID:checkboxDT:1:usermail').innerHTML="Please Enter Email ID";
					 		row2=false;
					 	 }else{
					 		if (input13 != null) {
								{
									if (emailv.test(input13)) {
										document.getElementById('userfom:panelID:checkboxDT:1:inputmail').style.borderColor = "green";
								  		document.getElementById('userfom:panelID:checkboxDT:1:usermail').innerHTML="";
								  		statususermail1=true;row2=true;c++;
									}
									else {
										document.getElementById('userfom:panelID:checkboxDT:1:inputmail').style.borderColor = "red";
								 		document.getElementById('userfom:panelID:checkboxDT:1:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
								 		row2=false;
									}
								}
							}	  		
					 	 } 
							if (input14 == null || input14 == "") {
						 		document.getElementById('userfom:panelID:checkboxDT:1:inputphone').style.borderColor = "red";
						 		document.getElementById('userfom:panelID:checkboxDT:1:userphone').innerHTML="Please Enter Phone Number";
						 		row2=false;
						 	 }
							 else{
						 		if (input14 != null) {
									{
										if (phonev.test(input14)) {
											document.getElementById('userfom:panelID:checkboxDT:1:inputphone').style.borderColor = "green";
									  		document.getElementById('userfom:panelID:checkboxDT:1:userphone').innerHTML="";
									  		statusphn1=true;row2=true;c++;
										}
										else {
											document.getElementById('userfom:panelID:checkboxDT:1:inputphone').style.borderColor = "red";
									 		document.getElementById('userfom:panelID:checkboxDT:1:userphone').innerHTML="Phone Number Should be 10 digit Number";
									 		row2=false;
											}
										}
									}	  		
						 	 	} 
					}
					if(c==4){
						if(row2==true){
							/*3 rd row*/
							try{
								var status2=false;var statuspass2=false;var statususermail2=false;var statusphn2=false;			
								var input21=document.getElementById('userfom:panelID:checkboxDT:2:inputuser').value;
								var input22=document.getElementById('userfom:panelID:checkboxDT:2:inputpwd').value;
								var input23=document.getElementById('userfom:panelID:checkboxDT:2:inputmail').value;
								var input24=document.getElementById('userfom:panelID:checkboxDT:2:inputphone').value;
								if(input21 !=null){
									status2=true;
								}						
								if(input22 !=null){
									statuspass2=true;
									}						
								if(input23 !=null){
									statususermail2=true;
									}						
								if(input24 !=null){
									statusphn2=true;	
									}
								if(status2==true || statuspass2==true || statususermail2==true || statusphn2==true){
									if (input21 == null || input21 == "") {
								 		document.getElementById('userfom:panelID:checkboxDT:2:inputuser').style.borderColor = "red";
								 		document.getElementById('userfom:panelID:checkboxDT:2:usererror').innerHTML="Please Enter User Name";
								 		row3=false;
								 	 }else{
								 		if (input21 != null) {
											{
												if (namevalidation.test(input21)) {
													document.getElementById('userfom:panelID:checkboxDT:2:inputuser').style.borderColor = "green";
											  		document.getElementById('userfom:panelID:checkboxDT:2:usererror').innerHTML="";
											  		status2=true;row3=true;c1++;
												
												} else {
													document.getElementById('userfom:panelID:checkboxDT:2:inputuser').style.borderColor = "red";
											 		document.getElementById('userfom:panelID:checkboxDT:2:usererror').innerHTML="User Name Atleast 3 charactor";
											 		row3=true;
												}
											}
								 		}
								 	 }
									if (input22 == null || input22 == "") {
								 		document.getElementById('userfom:panelID:checkboxDT:2:inputpwd').style.borderColor = "red";
								 		document.getElementById('userfom:panelID:checkboxDT:2:userpwd').innerHTML="Please Enter Password";
								 		row3=false;
								 	 }else
								 	 {
								 		if (input22 != null) {
											{
												if (passwordv.test(input22)) {
														document.getElementById('userfom:panelID:checkboxDT:2:inputpwd').style.borderColor = "green";
												  		document.getElementById('userfom:panelID:checkboxDT:2:userpwd').innerHTML="";	
												  		statuspass2=true;row3=true;c1++;
												}
												else
												{
													document.getElementById('userfom:panelID:checkboxDT:2:inputpwd').style.borderColor = "red";
											 		document.getElementById('userfom:panelID:checkboxDT:2:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
											 		row3=false;
												}
											}
										}	  		
								 	 } 
									if (input23 == null || input23 == "") {
								 		document.getElementById('userfom:panelID:checkboxDT:2:inputmail').style.borderColor = "red";
								 		document.getElementById('userfom:panelID:checkboxDT:2:usermail').innerHTML="Please Enter Email ID";
								 		row3=false;
								 	 }else{
								 		if (input23 != null) {
											{
												if (emailv.test(input23)) {
													document.getElementById('userfom:panelID:checkboxDT:2:inputmail').style.borderColor = "green";
											  		document.getElementById('userfom:panelID:checkboxDT:2:usermail').innerHTML="";
											  		statususermail2=true;row3=true;c1++;
												}
												else {
													document.getElementById('userfom:panelID:checkboxDT:2:inputmail').style.borderColor = "red";
											 		document.getElementById('userfom:panelID:checkboxDT:2:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
											 		row3=false;
												}
											}
										}	  		
								 	 } 
										if (input24 == null || input24 == "") {
									 		document.getElementById('userfom:panelID:checkboxDT:2:inputphone').style.borderColor = "red";
									 		document.getElementById('userfom:panelID:checkboxDT:2:userphone').innerHTML="Please Enter Phone Number";
									 		row3=false;
									 	 }
										 else{
									 		if (input24 != null) {
												{
													if (phonev.test(input24)) {
														document.getElementById('userfom:panelID:checkboxDT:2:inputphone').style.borderColor = "green";
												  		document.getElementById('userfom:panelID:checkboxDT:2:userphone').innerHTML="";
												  		statusphn2=true;row3=true;c1++;
													}
													else {
														document.getElementById('userfom:panelID:checkboxDT:2:inputphone').style.borderColor = "red";
												 		document.getElementById('userfom:panelID:checkboxDT:2:userphone').innerHTML="Phone Number Should be 10 digit Number";
												 		row3=false;
														}
													}
												}	  		
									 	 	}
								}
								if(c1==4){
									if(row3==true){
										/*3 rd row*/
										try{										
											var status3=false;var statuspass3=false;var statususermail3=false;var statusphn3=false;			
											var input31=document.getElementById('userfom:panelID:checkboxDT:3:inputuser').value;
											
											if(input31 !=null){
												status3=true;
											}
											var input32=document.getElementById('userfom:panelID:checkboxDT:3:inputpwd').value;
											if(input32 !=null){
												statuspass3=true;
												}
											var input33=document.getElementById('userfom:panelID:checkboxDT:3:inputmail').value;
											if(input33 !=null){
												statususermail3=true;
												}
											var input34=document.getElementById('userfom:panelID:checkboxDT:3:inputphone').value;
											if(input34 !=null){
												statusphn3=true;	
												}
											if(status3==true || statuspass3==true || statususermail3==true || statusphn3==true){
												if (input31 == null || input31 == "") {
											 		document.getElementById('userfom:panelID:checkboxDT:3:inputuser').style.borderColor = "red";
											 		document.getElementById('userfom:panelID:checkboxDT:3:usererror').innerHTML="Please Enter User Name";
											 		row4=false;
											 	 }else{
											 		if (input31 != null) {
														{
															if (namevalidation.test(input31)) {
																document.getElementById('userfom:panelID:checkboxDT:3:inputuser').style.borderColor = "green";
														  		document.getElementById('userfom:panelID:checkboxDT:3:usererror').innerHTML="";
														  		status3=true;row4=true;c2++;
															
															} else {
																document.getElementById('userfom:panelID:checkboxDT:3:inputuser').style.borderColor = "red";
														 		document.getElementById('userfom:panelID:checkboxDT:3:usererror').innerHTML="User Name Atleast 3 charactor";
														 		row4=true;
															}
														}
											 		}
											 	 }
												if (input32 == null || input32 == "") {
											 		document.getElementById('userfom:panelID:checkboxDT:3:inputpwd').style.borderColor = "red";
											 		document.getElementById('userfom:panelID:checkboxDT:3:userpwd').innerHTML="Please Enter Password";
											 		row4=false;
											 	 }else
											 	 {
											 		if (input32 != null) {
														{
															if (passwordv.test(input32)) {
																	document.getElementById('userfom:panelID:checkboxDT:3:inputpwd').style.borderColor = "green";
															  		document.getElementById('userfom:panelID:checkboxDT:3:userpwd').innerHTML="";	
															  		statuspass3=true;row4=true;c2++;
															}
															else
															{
																document.getElementById('userfom:panelID:checkboxDT:3:inputpwd').style.borderColor = "red";
														 		document.getElementById('userfom:panelID:checkboxDT:3:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
														 		row4=false;
															}
														}
													}	  		
											 	 } 
												if (input33 == null || input33 == "") {
											 		document.getElementById('userfom:panelID:checkboxDT:3:inputmail').style.borderColor = "red";
											 		document.getElementById('userfom:panelID:checkboxDT:3:usermail').innerHTML="Please Enter Email ID";
											 		row4=false;
											 	 }else{
											 		if (input33 != null) {
														{
															if (emailv.test(input33)) {
																document.getElementById('userfom:panelID:checkboxDT:3:inputmail').style.borderColor = "green";
														  		document.getElementById('userfom:panelID:checkboxDT:3:usermail').innerHTML="";
														  		statususermail3=true;row4=true;c2++;
															}
															else {
																document.getElementById('userfom:panelID:checkboxDT:3:inputmail').style.borderColor = "red";
														 		document.getElementById('userfom:panelID:checkboxDT:3:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
														 		row4=false;
															}
														}
													}	  		
											 	 } 
													if (input34 == null || input34 == "") {
												 		document.getElementById('userfom:panelID:checkboxDT:3:inputphone').style.borderColor = "red";
												 		document.getElementById('userfom:panelID:checkboxDT:3:userphone').innerHTML="Please Enter Phone Number";
												 		row4=false;
												 	 }
													 else{
												 		if (input34 != null) {
															{
																if (phonev.test(input34)) {
																	document.getElementById('userfom:panelID:checkboxDT:3:inputphone').style.borderColor = "green";
															  		document.getElementById('userfom:panelID:checkboxDT:3:userphone').innerHTML="";
															  		statusphn3=true;row4=true;c2++;
																}
																else {
																	document.getElementById('userfom:panelID:checkboxDT:3:inputphone').style.borderColor = "red";
															 		document.getElementById('userfom:panelID:checkboxDT:3:userphone').innerHTML="Phone Number Should be 10 digit Number";
															 		row4=false;
																	}
																}
															}	  		
												 	 	} 
											}
											if(c2==4){
												if(row4==true){
													
													/*4 th row*/
													try{
														var status4=false;var statuspass4=false;var statususermail4=false;var statusphn4=false;			
														var input41=document.getElementById('userfom:panelID:checkboxDT:4:inputuser').value;
														
														if(input41 !=null){
															status4=true;
														}
														var input42=document.getElementById('userfom:panelID:checkboxDT:4:inputpwd').value;
														if(input42 !=null){
															statuspass4=true;
															}
														var input43=document.getElementById('userfom:panelID:checkboxDT:4:inputmail').value;
														if(input43 !=null){
															statususermail4=true;
															}
														var input44=document.getElementById('userfom:panelID:checkboxDT:4:inputphone').value;
														if(input44 !=null){
															statusphn4=true;	
															}
														if(status4==true || statuspass4==true || statususermail4==true || statusphn4==true){
															if (input41 == null || input41 == "") {
														 		document.getElementById('userfom:panelID:checkboxDT:4:inputuser').style.borderColor = "red";
														 		document.getElementById('userfom:panelID:checkboxDT:4:usererror').innerHTML="Please Enter User Name";
														 		row5=false;
														 	 }else{
														 		if (input41 != null) {
																	{
																		if (namevalidation.test(input41)) {
																			document.getElementById('userfom:panelID:checkboxDT:4:inputuser').style.borderColor = "green";
																	  		document.getElementById('userfom:panelID:checkboxDT:4:usererror').innerHTML="";
																	  		status4=true;row5=true;c3++;
																		
																		} else {
																			document.getElementById('userfom:panelID:checkboxDT:4:inputuser').style.borderColor = "red";
																	 		document.getElementById('userfom:panelID:checkboxDT:4:usererror').innerHTML="User Name Atleast 3 charactor";
																	 		row5=true;
																		}
																	}
														 		}
														 	 }
															if (input42 == null || input42 == "") {
														 		document.getElementById('userfom:panelID:checkboxDT:4:inputpwd').style.borderColor = "red";
														 		document.getElementById('userfom:panelID:checkboxDT:4:userpwd').innerHTML="Please Enter Password";
														 		row5=false;
														 	 }else
														 	 {
														 		if (input42 != null) {
																	{
																		if (passwordv.test(input42)) {
																				document.getElementById('userfom:panelID:checkboxDT:4:inputpwd').style.borderColor = "green";
																		  		document.getElementById('userfom:panelID:checkboxDT:4:userpwd').innerHTML="";	
																		  		statuspass4=true;row5=true;c3++;
																		}
																		else
																		{
																			document.getElementById('userfom:panelID:checkboxDT:4:inputpwd').style.borderColor = "red";
																	 		document.getElementById('userfom:panelID:checkboxDT:4:userpwd').innerHTML="Minimum 6 Letters (eg:aa@111a)";
																	 		row5=false;
																		}
																	}
																}	  		
														 	 } 
															if (input43 == null || input43 == "") {
														 		document.getElementById('userfom:panelID:checkboxDT:4:inputmail').style.borderColor = "red";
														 		document.getElementById('userfom:panelID:checkboxDT:4:usermail').innerHTML="Please Enter Email ID";
														 		row5=false;
														 	 }else{
														 		if (input43 != null) {
																	{
																		if (emailv.test(input43)) {
																			document.getElementById('userfom:panelID:checkboxDT:4:inputmail').style.borderColor = "green";
																	  		document.getElementById('userfom:panelID:checkboxDT:4:usermail').innerHTML="";
																	  		statususermail4=true;row5=true;c3++;
																		}
																		else {
																			document.getElementById('userfom:panelID:checkboxDT:4:inputmail').style.borderColor = "red";
																	 		document.getElementById('userfom:panelID:checkboxDT:4:usermail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
																	 		row5=false;
																		}
																	}
																}	  		
														 	 } 
																if (input44 == null || input44 == "") {
															 		document.getElementById('userfom:panelID:checkboxDT:4:inputphone').style.borderColor = "red";
															 		document.getElementById('userfom:panelID:checkboxDT:4:userphone').innerHTML="Please Enter Phone Number";
															 		row5=false;
															 	 }
																 else{
															 		if (input44 != null) {
																		{
																			if (phonev.test(input44)) {
																				document.getElementById('userfom:panelID:checkboxDT:4:inputphone').style.borderColor = "green";
																		  		document.getElementById('userfom:panelID:checkboxDT:4:userphone').innerHTML="";
																		  		statusphn4=true;row5=true;c3++;
																			}
																			else {
																				document.getElementById('userfom:panelID:checkboxDT:4:inputphone').style.borderColor = "red";
																		 		document.getElementById('userfom:panelID:checkboxDT:4:userphone').innerHTML="Phone Number Should be 10 digit Number";
																		 		row5=false;
																				}
																			}
																		}	  		
															 	 	}
														}
														if(c3==4){
															document.getElementById('userfom:panelID:rowID').click();	
														}
													}
													catch(e2){
														document.getElementById('userfom:panelID:rowID').click();	
													}
													
													}
											}
											
										}
										catch(e1){
											document.getElementById('userfom:panelID:rowID').click();	
										}
									}
								}
							}
							catch(ee){
								document.getElementById('userfom:panelID:rowID').click();	
							}
							
							}					
					}
				}
				catch(e){					
						document.getElementById('userfom:panelID:rowID').click();	
				}
			}
		}

	}
	catch(e){
		document.getElementById('userfom:panelID:rowID').click();	
	}		
}