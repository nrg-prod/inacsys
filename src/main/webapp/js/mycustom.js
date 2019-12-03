/* check front End validation*/ 
/*Hide dialog Box when Page is loading(Refreshed) */
var newwindow;
$(document).ready(function() {
	remoteDialog(),remoteDialog1(),remoteDialog2();
	
});

function nameValidation(obj1,obj2) {
	try {
		var namevalidation = /^[a-zA-Z ]{3,30}$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="<img src='../../images/untick.png'/>";
		} else {
			if (input != null) {
				{
					if (namevalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="<img src='../../images/tick_white.png'/>";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="<img src='../../images/untick.png'/>";
					}
				}
			}
		}
	} catch (err) {
	}
}


function phoneNumberValidation(obj1,obj2) {
	try {
		var namevalidation = /^[0-9]{10,12}$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="<img src='../../images/untick.png'/>";
		} else {
			if (input != null) {
				{
					if (namevalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="<img src='../../images/tick_white.png'/>";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="<img src='../../images/untick.png'/>";
					}
				}
			}
		}
	} catch (err) {
	}
}

function emailValidation(obj1,obj2) {
	try {
		
		var input = document.getElementById(obj1).value;
	    var atpos = input.indexOf("@");
	    var dotpos = input.lastIndexOf(".");
	   

	   

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
/*			document.getElementById(obj2).innerHTML="<img src='../../images/untick.png'/>";
*/		document.getElementById(obj2).innerHTML="Please Enter the Email ID";
			} else {
			if (input != null) {
				{
					 if (atpos<1 || dotpos<atpos+2 || dotpos+2>=input.length) {
						 document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
						
					} else {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";
						
					}
				}
			}
		}
	} catch (err) {
	}
}

function addressValidation(obj1,obj2) {
	try {
		var namevalidation =/[ A-Za-z0-9_@./#&+-]*$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the Address";
		} else {
			if (input != null) {
				{
					if (namevalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="Please Enter the Valid Address";
					}
				}
			}
		}
	} catch (err) {
	}
}

function dropDownValidation(obj1,obj2,obj3) {
	var input = PF(obj1).getSelectedValue();
	
/*		alert(input.localeCompare("select"));
*/		if(input == "select"){
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Choose the Country name";
		} else {
				document.getElementById(obj3).style.borderColor = "#D3D3D3";
				document.getElementById(obj2).innerHTML="";
		}
	
}


function vendorValidation(){
	var phonevalidation =/^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/;
	try {
		var vendor_name = document.getElementById('center_content:ven:vfname').value;
		var telephone = document.getElementById('center_content:ven:vtpno').value;
		var country = document.getElementById('center_content:ven:tabviewid:vcoun').value;
		var email_id = document.getElementById('center_content:ven:vemail').value;
		var vendor_name_st=false;
		var telephone_st=false;
		var country_st=false;
		var email_id_st=false;
		var atpos = email_id.indexOf("@");
	    var dotpos = email_id.lastIndexOf(".");
	    
	    /* Vendor_name  */
		if(vendor_name == null || vendor_name == ""){
			document.getElementById('center_content:ven:vfname').style.borderColor = "red";
			document.getElementById('vfnameicon').innerHTML="Please Enter the Firm Name";
		}else{
			document.getElementById('center_content:ven:vfname').style.borderColor = "#d3d3d3";
			document.getElementById('vfnameicon').innerHTML="";
			vendornamecheck([{name:'param1', value:vendor_name}]);
			vendor_name_st=true;
		}
		
		/* Telephone */
		if(telephone == null || telephone == ""){
			document.getElementById('center_content:ven:vtpno').style.borderColor = "red";
			document.getElementById('vtpnoicon').innerHTML="Please Enter the Telephone Number";
		}else{
			if (telephone != null) {
					if (phonevalidation.test(telephone)) {
						document.getElementById('center_content:ven:vtpno').style.borderColor = "#D3D3D3";
						document.getElementById('vtpnoicon').innerHTML="";
							telephone_st=true;
					}
					else{
						document.getElementById('center_content:ven:vtpno').style.borderColor = "red";
						document.getElementById('vtpnoicon').innerHTML="Please Enter the Valid Telephone Number";
					}
			}
		}
		
		/* Country  */
		if(country == "select"){
			document.getElementById('center_content:ven:tabviewid:vcoun').style.borderColor = "red";
			document.getElementById('vcounicon').innerHTML="Please Choose the Country";
		} else {
				document.getElementById('center_content:ven:tabviewid:vcoun').style.borderColor = "#D3D3D3";
				document.getElementById('vcounicon').innerHTML="";
				country_st=true;
		}
		
		/* Email  */
		if (email_id == null || email_id == "") {
			document.getElementById('center_content:ven:vemail').style.borderColor = "red";
			document.getElementById('vemailicon').innerHTML="Please Enter the Email ID";
		} else {
			if (email_id != null) {
				{
					if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email_id.length) {
						 document.getElementById('center_content:ven:vemail').style.borderColor = "red";
						 document.getElementById('vemailicon').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
						
					} else {
						document.getElementById('center_content:ven:vemail').style.borderColor = "#D3D3D3";
						document.getElementById('vemailicon').innerHTML="";
						email_id_st=true;
					}
				}
			}
		}
		if((vendor_name_st == true) && (telephone_st == true) && (country_st == true) && (email_id_st==true)){
			vendornamecheck([{name:'param1', value:vendor_name}]);
			document.getElementById('center_content:ven:hidenBtn').click();
		}
	}catch (err) {
		alert(err);
	}
}

function vendorValidation2(){
	var phonevalidation =/^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/;
	var numbervalidation =  /^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
	try {
		var product_price = document.getElementById('center_content:ven:prodprice').value;
		var vendor_name = document.getElementById('center_content:ven:vfname').value;
		var telephone = document.getElementById('center_content:ven:vtpno').value;
		var country = document.getElementById('center_content:ven:tabviewid:vcoun').value;
		var email_id = document.getElementById('center_content:ven:vemail').value;
		var vendor_name_st=false;
		var telephone_st=false;
		var country_st=false;
		var email_id_st=false;
		var prodprice_st=false;
		var atpos = email_id.indexOf("@");
	    var dotpos = email_id.lastIndexOf(".");
	    
	    /* Vendor_name  */
		if(vendor_name == null || vendor_name == ""){
			document.getElementById('center_content:ven:vfname').style.borderColor = "red";
			document.getElementById('vfnameicon').innerHTML="Please Enter the Firm Name";
		}else{
			document.getElementById('center_content:ven:vfname').style.borderColor = "#d3d3d3";
			document.getElementById('vfnameicon').innerHTML="";
			vendornamecheck([{name:'param1', value:vendor_name}]);
			vendor_name_st=true;
		}
		
		/* Telephone */
		if(telephone == null || telephone == ""){
			document.getElementById('center_content:ven:vtpno').style.borderColor = "red";
			document.getElementById('vtpnoicon').innerHTML="Please Enter the Telephone Number";
		}else{
			if (telephone != null) {
					if (phonevalidation.test(telephone)) {
						document.getElementById('center_content:ven:vtpno').style.borderColor = "#D3D3D3";
						document.getElementById('vtpnoicon').innerHTML="";
						telephone_st=true;
					}
					else{
						document.getElementById('center_content:ven:vtpno').style.borderColor = "red";
						document.getElementById('vtpnoicon').innerHTML="Please Enter the Valid Telephone Number";
					}
			}
		}
		
		/* Country  */
		if(country == "select"){
			document.getElementById('center_content:ven:tabviewid:vcoun').style.borderColor = "red";
			document.getElementById('vcounicon').innerHTML="Please Choose the Country";
		} else {
				document.getElementById('center_content:ven:tabviewid:vcoun').style.borderColor = "#D3D3D3";
				document.getElementById('vcounicon').innerHTML="";
				country_st=true;
		}
		
		/* Email  */
		if (email_id == null || email_id == "") {
			document.getElementById('center_content:ven:vemail').style.borderColor = "red";
			document.getElementById('vemailicon').innerHTML="Please Enter the Email ID";
		} else {
			if (email_id != null) {
				{
					if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email_id.length) {
						 document.getElementById('center_content:ven:vemail').style.borderColor = "red";
						 document.getElementById('vemailicon').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
						
					} else {
						document.getElementById('center_content:ven:vemail').style.borderColor = "#D3D3D3";
						document.getElementById('vemailicon').innerHTML="";
						email_id_st=true;
					}
				}
			}
		}
		/* Product Price */
		if (product_price == null || product_price == "") {
			document.getElementById('center_content:ven:prodprice').style.borderColor = "red";
			document.getElementById('vprodpriceicon').innerHTML="Please Enter the Product Price";
		} else {
			if (product_price != null) {
				{
					if (numbervalidation.test(product_price)) {
						document.getElementById('center_content:ven:prodprice').style.borderColor = "#D3D3D3";
						document.getElementById('vprodpriceicon').innerHTML="";
						prodprice_st=true;
					} else {
						document.getElementById('center_content:ven:prodprice').style.borderColor = "red";
						document.getElementById('vprodpriceicon').innerHTML="Please Enter the Valid Price";
					}
				}
			}
		}
		if((vendor_name_st == true) && (telephone_st == true) && (country_st == true) && (email_id_st==true) && (prodprice_st==true)){
			vendornamecheck([{name:'param1', value:vendor_name}]);
			document.getElementById('center_content:hidenBtn2').click();
		}
	}catch (err) {
		alert(err);
	}
}

function vendorname(obj1,obj2) {
		var input = document.getElementById(obj1).value;
		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter Firm Name";			
		} else {
			document.getElementById(obj1).style.borderColor = "#d3d3d3";
			document.getElementById(obj2).innerHTML="";
			vendornamecheck([{name:'param1', value:input}]);
		}
}


function taxnumber(obj1,obj2) {
	try {
		var phonevalidation = /^[0-9]{10,12}$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the Tax Number";
		} else {
			if (input != null) {
				{
					if (phonevalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="Tax Number Should be 10 digit Number";
					}
				}
			}
		}
	} catch (err) {
	}
}

function natureofbusiness(obj1,obj2) {
	try {
		var namevalidation = /^[a-zA-Z ]{3,30}$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the Nature of Business";
		} else {
			if (input != null) {
				{
					if (namevalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="Nature of Business should be of atleast 3 characters";
					}
				}
			}
		}
	} catch (err) {
	}
}

function city(obj1,obj2) {
	try {
		var namevalidation = /^[a-zA-Z ]{3,30}$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the City";
		} else {
			if (input != null) {
				{
					if (namevalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="City should be Alphabet";
					}
				}
			}
		}
	} catch (err) {
	}
}

function state(obj1,obj2) {
	try {
		var namevalidation = /^[a-zA-Z ]{3,30}$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the State";
		} else {
			if (input != null) {
				{
					if (namevalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="State should be of atleast 3 characters";
					}
				}
			}
		}
	} catch (err) {
	}
}

function phonenumber(obj1,obj2) {
	try {
		var phonevalidation = /^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the Phone Number";
		} else {
			if (input != null) {
				 if (phonevalidation.test(input)) {
					document.getElementById(obj1).style.borderColor = "#D3D3D3";
					document.getElementById(obj2).innerHTML="";
				} else{
					document.getElementById(obj1).style.borderColor = "red";
					document.getElementById(obj2).innerHTML="Please Enter Valid Phone Number";
				}
			}
		}
	} catch (err) {
	}
}

function personincharge(obj1,obj2) {
	try {
		var namevalidation = /^[a-zA-Z ]{3,30}$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the Person Incharge";
		} else {
			if (input != null) {
				{
					if (namevalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="Person In Charge should be Alphabet";
					}
				}
			}
		}
	} catch (err) {
	}
}

function checkFail(){
	var vendor_name = document.getElementById('center_content:ven:vfname').value;
	if(vendor_name != null || vendor_name != ""){
		document.getElementById('center_content:ven:vfname').style.borderColor = "red";
		document.getElementById('vfnameicon').innerHTML="Already Exist";
	}	
} 

function checkSuccess(){
	var vendor_name = document.getElementById('center_content:ven:vfname').value;
	if(vendor_name != null || vendor_name != ""){
		document.getElementById('center_content:ven:vfname').style.borderColor = "#D3D3D3";
		document.getElementById('vfnameicon').innerHTML="";
	}	
} 

function popitup(url) {
	newwindow=window.open(url,'name','height=430,width=700');
	if (window.focus) {
		newwindow.focus();
	}
		return false;
}
function hidemypopwindow(){
	var closewondow = window.self;
	closewondow.opener = window.self;
	closewondow.close();

}
//prema begin 28/04/2016 reset in vendor registration

function vendorclear()
{
	 document.getElementById('center_content:ven:vfname').value= "";
	 document.getElementById('center_content:ven:vfname').style.borderColor="";
	 document.getElementById('vfnameicon').innerHTML="";
	 document.getElementById('center_content:ven:vTaxNo').value= "";
	 document.getElementById('center_content:ven:vTaxNo').style.borderColor="";
	 document.getElementById('vTaxNoicon').innerHTML="";
	 document.getElementById('center_content:ven:vNoB').value= "";
	 document.getElementById('center_content:ven:vcity').value= "";
	 document.getElementById('center_content:ven:vcity').style.borderColor="";
	 document.getElementById('vcityicon').innerHTML="";
	 document.getElementById('center_content:ven:vpinc').value= "";
	 document.getElementById('center_content:ven:vpinc').style.borderColor="";
	 document.getElementById('vpincicon').innerHTML="";
	 document.getElementById('center_content:ven:vtpno').value= "";
	 document.getElementById('center_content:ven:vtpno').style.borderColor="";
	 document.getElementById('vtpnoicon').innerHTML="";
	 document.getElementById('center_content:ven:vemail').value="";
	 document.getElementById('center_content:ven:vemail').style.borderColor="";
	 document.getElementById('vemailicon').innerHTML="";
	 document.getElementById('center_content:ven:vaddr').value="";
	 document.getElementById('center_content:ven:vaddr').style.borderColor="";
	 document.getElementById('vaddricon').innerHTML="";
	 document.getElementById('center_content:ven:vftype').value="";
	 document.getElementById('center_content:ven:vweb').value="";
	 document.getElementById('center_content:ven:vFaxNo').value="";
	 document.getElementById('center_content:ven:vNoB').value="";
	 document.getElementById('center_content:ven:vstate').value="";
	 document.getElementById('center_content:ven:vnote').value="";
	 document.getElementById('center_content:ven:vcoun_label').innerHTML = '---Select---';
	 document.getElementById('center_content:ven:vcoun').style.borderColor = "";
	 document.getElementById('vcounicon').innerHTML="";
}
//prema end 28/04/2016

//client side validation for Customer Registration

function customerValidation(){
	try {
			//var namevalidation = /^[a-zA-Z ]{3,30}$/;
			//var addressvalidation =/[ A-Za-z0-9_@./#&+-]*$/;
			var emailvalidation = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
			var phonevalidation =/^(\$|)([10-13]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
			//var taxvalidation=/^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
			//var websitevalidation= /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/|www\.)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
			
			//var custmobile = document.getElementById('center_content:cus:cuspno').value;
			//var cuscity = document.getElementById('center_content:cus:tabviewid:ccity1').value;
			//var paymentType=document.getElementById('center_content:cus:patyp').value;
			//var faxnumber=document.getElementById('center_content:cus:cfax').value;
			//var licencenumber=document.getElementById('center_content:cus:tlic').value;
			//var website=document.getElementById('center_content:cus:web').value;
			
			//var date = document.getElementById('center_content:cus:cdate_input').value;
			var customer_name = document.getElementById('center_content:cus:cfname').value;
			var custelephone = document.getElementById('center_content:cus:cusmno').value;
			var category = document.getElementById('center_content:cus:ccate').value;
			var country = document.getElementById('center_content:cus:tabviewid:ccoun1').value;
			var email_id=document.getElementById('center_content:cus:cemail').value;
			var atpos = email_id.indexOf("@");
		    var dotpos = email_id.lastIndexOf(".");
		    
			//var cuscity_st=false;
			//var custmobile_st=false;
		    //var cupayment=false;
			//var cuday=false;
			//var fax_st=false;
			//var licencenumber_st=false;
		    //var website_st=false;
		    //var cusdate_st=false;
			var custelephone_st=false;
			var customer_name_st=false;
			var cuscategory_st=false;
			var cuscountry_st=false;
			var mail_st=false;
			
			if(customer_name == null || customer_name == ""){
				document.getElementById('center_content:cus:cfname').style.borderColor = "red";
				document.getElementById('cnameicon').innerHTML="Please Enter Customer Name";
			}else{
				document.getElementById('center_content:cus:cfname').style.borderColor = "#D3D3D3";
				document.getElementById('cnameicon').innerHTML="";
				customer_name_st=true;
			}
			if(custelephone == null || custelephone == ""){
				document.getElementById('center_content:cus:cusmno').style.borderColor = "red";
				document.getElementById('cuspnoicon').innerHTML="Please Enter the Phone Number";
			}else{
				if (custelephone != null){
					if (phonevalidation.test(custelephone)) {
								document.getElementById('center_content:cus:cusmno').style.borderColor = "#D3D3D3";
								document.getElementById('cuspnoicon').innerHTML="";
								custelephone_st=true;
					}
					else{
						document.getElementById('center_content:cus:cusmno').style.borderColor = "red";
						document.getElementById('cuspnoicon').innerHTML="Please Enter Valid Phone Number";
						}
					}
						
			}
			/*if(custmobile == null || custmobile == ""){
				document.getElementById('center_content:cus:cuspno').style.borderColor = "#D3D3D3";
				document.getElementById('custmnoicon').innerHTML="";
				custmobile_st=true;
			}
			else{
				if(custmobile != null) {
					{
						if (phonevalidation.test(custmobile)) {
							document.getElementById('center_content:cus:cuspno').style.borderColor = "#D3D3D3";
							document.getElementById('custmnoicon').innerHTML="";
							custmobile_st=true;
						} else {
							document.getElementById('center_content:cus:cuspno').style.borderColor = "red";
							document.getElementById('custmnoicon').innerHTML="Phone Number with in 13 digit Numbers";
						}
					}
				}
			}*/
			/*if(date == null || date ==""){
				document.getElementById('center_content:cus:cdate_input').style.borderColor = "red";
				document.getElementById('cdateicon').innerHTML="Please Select the Date";
				
			} else {
					document.getElementById('center_content:cus:cdate_input').style.borderColor = "#D3D3D3";
					document.getElementById('cdateicon').innerHTML="";
					cusdate_st=true;
						
			}*/
			/*if( cuscity == "" || cuscity==null){
				document.getElementById('center_content:cus:tabviewid:ccity1').style.borderColor = "red";
				document.getElementById('cuscityicon').innerHTML="Please Enter the City";
			}else{
				if (cuscity != null) {
					{
						if (namevalidation.test(cuscity)) {
							document.getElementById('center_content:cus:tabviewid:ccity1').style.borderColor = "#D3D3D3";
						document.getElementById('cuscityicon').innerHTML="";
						cuscity_st=true;
						} else {
							document.getElementById('center_content:cus:tabviewid:ccity1').style.borderColor = "red";
							document.getElementById('cuscityicon').innerHTML="City should be Alphabet";
						}
					}
				}
			}*/
			/*Trade Licence Number*/
			/*if(licencenumber == null || licencenumber == ""){
				document.getElementById('center_content:cus:tlic').style.borderColor = "#D3D3D3";
				document.getElementById('clicenseicon').innerHTML="";
				licencenumber_st=true;
			}
			else{
				if(licencenumber != null) {
					{
						if (taxvalidation.test(licencenumber)) {
							document.getElementById('center_content:cus:tlic').style.borderColor = "#D3D3D3";
							document.getElementById('clicenseicon').innerHTML="";
							licencenumber_st=true;
						} else {
							document.getElementById('center_content:cus:tlic').style.borderColor = "red";
							document.getElementById('clicenseicon').innerHTML="Please Enter valid Licence Number";
						}
					}
				}
			}*/
			/*if(faxnumber == null || faxnumber == ""){
				document.getElementById('center_content:cus:cfax').style.borderColor = "#D3D3D3";
				document.getElementById('cfaxicon').innerHTML="";
				fax_st=true;
			}
			else{
				if(faxnumber != null) {
					{
						if (taxvalidation.test(faxnumber)) {
							document.getElementById('center_content:cus:cfax').style.borderColor = "#D3D3D3";
							document.getElementById('cfaxicon').innerHTML="";
							fax_st=true;
						} else {
							document.getElementById('center_content:cus:cfax').style.borderColor = "red";
							document.getElementById('cfaxicon').innerHTML="Please Enter valid Fax Number";
						}
					}
				}
			}*/
			if (email_id == null || email_id == "") {
				document.getElementById('center_content:cus:cemail').style.borderColor = "red";
				document.getElementById('cemailicon').innerHTML="Please Enter the Email ID";
			} else {
				if (email_id != null) {
					{
						if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email_id.length) {
							document.getElementById('center_content:cus:cemail').style.borderColor = "red";
							document.getElementById('cemailicon').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
						} else {
							document.getElementById('center_content:cus:cemail').style.borderColor = "#D3D3D3";
							document.getElementById('cemailicon').innerHTML="";
							mail_st=true;
						}
					}
				}
			}
			/*if(website == null || website == ""){
				document.getElementById('center_content:cus:web').style.borderColor = "#D3D3D3";
				document.getElementById('cwebicon').innerHTML="";
				website_st=true;
			}
			else{
				if(website != null) {
					{
						if (websitevalidation.test(website)) {
							document.getElementById('center_content:cus:web').style.borderColor = "#D3D3D3";
							document.getElementById('cwebicon').innerHTML="";
							website_st=true;
						} else {
							document.getElementById('center_content:cus:web').style.borderColor = "red";
							document.getElementById('cwebicon').innerHTML="Please Enter valid Website";
						}
					}
				}
			}*/
			if(category == "select"){
				document.getElementById('center_content:cus:ccate').style.borderColor = "red";
				document.getElementById('ccateicon').innerHTML="Please Select the Category";
				
			}else {
				document.getElementById('center_content:cus:ccate').style.borderColor = "#D3D3D3";
				document.getElementById('ccateicon').innerHTML="";
				cuscategory_st=true;
			}
			if(country == "select"){
				document.getElementById('center_content:cus:tabviewid:ccoun1').style.borderColor = "red";
				document.getElementById('ccounicon').innerHTML="Please Select the Country";
				
			} else {
					document.getElementById('center_content:cus:tabviewid:ccoun1').style.borderColor = "#D3D3D3";
						document.getElementById('ccounicon').innerHTML="";
						cuscountry_st=true;
						
			}
			/*if(paymentType=="select"){
				document.getElementById('center_content:cus:patyp').style.borderColor="red";
				document.getElementById('paytype').innerHTML="Please Select the Paymenttype";
			}else{
					if(paymentType=="Credit"){
					if(document.getElementById('center_content:cus:days').value=="select" || document.getElementById('center_content:cus:days').value=="Add new"){
						document.getElementById('center_content:cus:days').style.borderColor="red";
						document.getElementById('creday').innerHTML="Please Select the Credit days";
					}else{
						document.getElementById('center_content:cus:days').style.borderColor="#D3D3D3";
						document.getElementById('creday').innerHTML="";
						cuday=true;
					}	
					}else if(paymentType=="Cash"){
						cuday=true;
					}
				document.getElementById('center_content:cus:patyp').style.borderColor="#D3D3D3";
				document.getElementById('paytype').innerHTML="";
				cupayment=true;
			}*/
			if((customer_name_st == true) && (custelephone_st == true) && (cuscategory_st==true) && (cuscountry_st==true) && (mail_st == true)){
				customernamecheck([{name:'param2', value:custelephone}]);
				document.getElementById('center_content:cus:hidenBtn').click();
			}
			
		}catch (err) {
			alert(err);
	}
	}


function customerpno(obj1,obj2) {
	
	var phonevalidation =/^(\$|)([10-13]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
	var input = document.getElementById(obj1).value;


	if (input == null || input == "") {
		document.getElementById(obj1).style.borderColor = "red";
		document.getElementById(obj2).innerHTML="Please Enter Phone Number";
		
	} else {
		if (input != null) 
			{
				if (phonevalidation.test(input)) {
					document.getElementById(obj1).style.borderColor = "#D3D3D3";
					document.getElementById(obj2).innerHTML="";
					customernamecheck([{name:'param2', value:input}]);
				} 
				else{
					document.getElementById(obj1).style.borderColor = "red";
					document.getElementById(obj2).innerHTML="Please Enter Valid Phone Number";
				}
			}
	}


}

function customername(obj1,obj2) {
	

	var input = document.getElementById(obj1).value;

	if (input == null || input == "") {
		document.getElementById(obj1).style.borderColor = "red";
		document.getElementById(obj2).innerHTML="Please Enter Customer Name";
		
	} else{
		document.getElementById(obj1).style.borderColor = "#D3D3D3";
		document.getElementById(obj2).innerHTML="";
	}
}
function checkFail1(){
	var custelephone = document.getElementById('center_content:cus:cuspno').value;
	if(custelephone != null || custelephone != ""){
		document.getElementById('center_content:cus:cusmno').style.borderColor = "red";
		document.getElementById('cuspnoicon').innerHTML="Already Exist";
	}	
} 

function checkSuccess1(){
	var custelephone = document.getElementById('center_content:cus:cusmno').value;
	if(custelephone != null || custelephone != ""){
		document.getElementById('center_content:cus:cusmno').style.borderColor = "#D3D3D3";
		document.getElementById('cuspnoicon').innerHTML="";
	}	
}

//prema begin 28/04/2016 reset in customer registration

function customerclear()
{
	 document.getElementById('center_content:cus:cname').value= "";
	 document.getElementById('center_content:cus:cname').style.borderColor="";
	 document.getElementById('cnameicon').innerHTML="";
	 document.getElementById('center_content:cus:ccity').value= "";
	 document.getElementById('center_content:cus:ccity').style.borderColor="";
	 document.getElementById('cuscityicon').innerHTML="";
	 document.getElementById('center_content:cus:cuspno').value= "";
	 document.getElementById('center_content:cus:cuspno').style.borderColor="";
	 document.getElementById('cuspnoicon').innerHTML="";
	 document.getElementById('center_content:cus:cstate').value="";
	 document.getElementById('center_content:cus:ctax').value="";
	 document.getElementById('center_content:cus:saddr').value="";
	 document.getElementById('center_content:cus:cnote').value="";
	 document.getElementById('center_content:cus:cemail').value="";
	 document.getElementById('center_content:cus:ccoun_label').innerHTML = '---Select---';
	 document.getElementById('center_content:cus:ccoun').style.borderColor="";
	 document.getElementById('ccounicon').innerHTML="";
	 document.getElementById('center_content:cus:ccate_label').innerHTML = '---Select---';
	 document.getElementById('center_content:cus:ccate').style.borderColor="";
	 document.getElementById('ccateicon').innerHTML="";
}
//prema end 28/04/2016

//prema begin 27/04/2016 client side validation for product registration

/*product name*/
function productname(obj1,obj2) {
	
	var namevalidation = /^(?=.*[A-Za-z])([a-zA-Z0-9,\\ \\'\\-\\.\\&\\/(\\s)+]*)$/;
	var input = document.getElementById(obj1).value;

	if (input == null || input == "") {
		document.getElementById(obj1).style.borderColor = "red";
		document.getElementById(obj2).innerHTML="Please Enter Product Name";
		
	} else {
		if (input != null) {
			{
				if (namevalidation.test(input)) {
					document.getElementById(obj1).style.borderColor = "#D3D3D3";
					document.getElementById(obj2).innerHTML="";
					productnamecheck([{name:'param3', value:input}]);
				} else {
					document.getElementById(obj1).style.borderColor = "red";
					document.getElementById(obj2).innerHTML="Please Enter valid Product Name";
					
				}
			}
		}
	}
}
/*product code*/
function productcode(obj1,obj2) {
	
	var namevalidation = /^(?=.*[A-Za-z])([a-zA-Z0-9,\\ \\'\\-\\.\\&\\/(\\s)+]*)$/;

	var productlimit = document.getElementById('center_content:product-reg:vproductLimit').value;

	if (productlimit == null || productlimit == "") {
		document.getElementById('center_content:product-reg:vproductLimit').style.borderColor = "red";
		document.getElementById('pcodeicon').innerHTML="Please Enter Product Code";
		
	} else {
		if(productlimit != null){
			if(namevalidation.test(productlimit)){
				document.getElementById('center_content:product-reg:vproductLimit').style.borderColor = "#D3D3D3";
				document.getElementById('pcodeicon').innerHTML="";
				productcodecheck([{name:'param4', value:productlimit}]);
			}
			else{
				document.getElementById('center_content:product-reg:vproductLimit').style.borderColor = "red";
				document.getElementById('pcodeicon').innerHTML="Please Enter valid Product Code";
			}
		}
		
	}
}
/*size*/
function productsize(obj1,obj2) {
	
	var namevalidation = /^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;

	var size_of_product = document.getElementById('center_content:product-reg:prod-size').value;

	if (size_of_product == null || size_of_product == "") {
		document.getElementById('center_content:product-reg:prod-size').style.borderColor = "red";
		document.getElementById('psizeicon').innerHTML="Please Enter Product Size";
		
	} else {
		if(namevalidation.test(size_of_product)){
			document.getElementById('center_content:product-reg:prod-size').style.borderColor = "#D3D3D3";
			document.getElementById('psizeicon').innerHTML="";
		}
		else{
			document.getElementById('center_content:product-reg:prod-size').style.borderColor = "red";
			document.getElementById('psizeicon').innerHTML="Please Enter valid Product Size";
		}
	}
}
/*Actual price*/
function productaprice(obj1,obj2) {
	
	try {
		var numbervalidation =  /^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the Product Actual Price";
		} else {
			if (input != null) {
				{
					if (numbervalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="Actual Price should be valid numbers";
					}
				}
			}
		}
	} catch (err) {
	}
}

/*Market price*/
function productmprice(obj1,obj2) {
	
	try {
		var numbervalidation =  /^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
		var input = document.getElementById(obj1).value;

		if (input == null || input == "") {
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Enter the Product Market Price";
		} else {
			if (input != null) {
				{
					if (numbervalidation.test(input)) {
						document.getElementById(obj1).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";

					} else {
						document.getElementById(obj1).style.borderColor = "red";
						document.getElementById(obj2).innerHTML="Market Price should be valid numbers";
					}
				}
			}
		}
	} catch (err) {
	}
}

/*product category*/
function dropDownCategory(obj1,obj2,obj3) {
		var input = PF(obj1).getSelectedValue();
		if(input == "select"){
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Choose the Category name";
		} else {
				document.getElementById(obj3).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";
				}
	
}
/*product Vendor*/
function dropDownVendor(obj1,obj2,obj3) {
	
		var input = PF(obj1).getSelectedValue();
		
		if(input == "select"){
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Choose the Vendor name";
		} else {
				document.getElementById(obj3).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";
				}
}
/*product Unit*/
function dropDownUnit(obj1,obj2,obj3) {
	var input = PF(obj1).getSelectedValue();
		
		if(input == "select"){
			document.getElementById(obj1).style.borderColor = "red";
			document.getElementById(obj2).innerHTML="Please Choose the Product Unit";
		} else {
				document.getElementById(obj3).style.borderColor = "#D3D3D3";
						document.getElementById(obj2).innerHTML="";
				}
	
}

/* submit button validation for product registration*/
function productValidation(){
	try{
	var namevalidation = /^(?=.*[A-Za-z])([a-zA-Z0-9,\\ \\'\\-\\.\\&\\/(\\s)+]*)$/;
	var numbervalidation = /^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
	var brandvalidation=  /^[a-zA-Z ]{3,30}$/;
	
	var pcategory = document.getElementById('center_content:product-reg:pcate').value;
	
	var pcode = document.getElementById('center_content:product-reg:vproductLimit').value;
	var pname = document.getElementById('center_content:product-reg:prod-name').value;
	//var psize = document.getElementById('center_content:product-reg:prod-size').value;
	
	/*var brand = document.getElementById('center_content:product-reg:vBrandNo').value;*/
	
	var standard = document.getElementById('center_content:product-reg:prod-standard').value;
	
	var pvendor = document.getElementById('center_content:product-reg:vendor-id').value;
	
	var paprice = document.getElementById('center_content:product-reg:prod-actual-prize').value;
	
	var pmprice = document.getElementById('center_content:product-reg:prod-market-prize').value;
	
	var punit = document.getElementById('center_content:product-reg:prod-unit').value;
	
	var pcategory_st=false;
	var pcode_st=false;
	var pname_st=false;
	//var psize_st=false;
	var pvendor_st=false;
	var paprice_st=false;
	var pmprice_st=false;
	var punit_st=false;
	var brand_st=false;
	var stand_st=false;
	
	/* category*/
	if(pcategory == "select"){
		document.getElementById('center_content:product-reg:pcate').style.borderColor = "red";
		document.getElementById('pcateicon').innerHTML="Please Choose the Category";
		
	} else {
			document.getElementById('center_content:product-reg:pcate').style.borderColor = "#D3D3D3";
				document.getElementById('pcateicon').innerHTML="";
				pcategory_st=true;
				
	}
	/*product code*/
	if (pcode == null || pcode == "") {
		document.getElementById('center_content:product-reg:vproductLimit').style.borderColor = "red";
		document.getElementById('pcodeicon').innerHTML="Please Enter Product Code";
		
	} else {
		if(pcode != null){
			if(namevalidation.test(pcode)){
				document.getElementById('center_content:product-reg:vproductLimit').style.borderColor = "#D3D3D3";
				document.getElementById('pcodeicon').innerHTML="";
				productcodecheck([{name:'param4', value:pcode}]);
				pcode_st=true;
			}
			else{
				document.getElementById('center_content:product-reg:vproductLimit').style.borderColor = "red";
				document.getElementById('pcodeicon').innerHTML="Please Enter valid Product Code";
			}
		}
		
	}
	
	/* product name*/
	if (pname == null || pname == "") {
		document.getElementById('center_content:product-reg:prod-name').style.borderColor = "red";
		document.getElementById('pnameicon').innerHTML="Please Enter Product Name";
		
	} else {
		if (pname != null) {
			
				if (namevalidation.test(pname)) {
					document.getElementById('center_content:product-reg:prod-name').style.borderColor = "#D3D3D3";
					document.getElementById('pnameicon').innerHTML="";
					productnamecheck([{name:'param3', value:pname}]);
					pname_st=true;
					
				} else {
					document.getElementById('center_content:product-reg:prod-name').style.borderColor = "red";
					document.getElementById('pnameicon').innerHTML="Please Enter valid Product Name";		
				}
			}
	}
	/*size*/
	/*if (psize == null || psize == "") {
		document.getElementById('center_content:product-reg:prod-size').style.borderColor = "red";
		document.getElementById('psizeicon').innerHTML="Please Enter Product Size";
		
	} else {
		if(psize != null){
			if(numbervalidation.test(psize)){
				document.getElementById('center_content:product-reg:prod-size').style.borderColor = "#D3D3D3";
				document.getElementById('psizeicon').innerHTML="";
				psize_st=true;
			}
			else{
				document.getElementById('center_content:product-reg:prod-size').style.borderColor = "red";
				document.getElementById('psizeicon').innerHTML="Please Enter valid Product Size";
			}
		}
		
	}*/
	/* Actual price*/
	if(paprice == null || paprice == ""){
		document.getElementById('center_content:product-reg:prod-actual-prize').style.borderColor = "red";
		document.getElementById('apriceicon').innerHTML="Please Enter the Product Actual price";
	}else{
		if (paprice != null) {
			{
				if (numbervalidation.test(paprice)) {
					document.getElementById('center_content:product-reg:prod-actual-prize').style.borderColor = "#D3D3D3";
				document.getElementById('apriceicon').innerHTML="";
				paprice_st=true;
				} else {
					document.getElementById('center_content:product-reg:prod-actual-prize').style.borderColor = "red";
					document.getElementById('apriceicon').innerHTML="Actual price should be numbers";
				}
			}
		}
	}
	/* Market price*/
	if(pmprice == null || pmprice == ""){
		document.getElementById('center_content:product-reg:prod-market-prize').style.borderColor = "red";
		document.getElementById('mpriceicon').innerHTML="Please Enter the Product Market price";
	}else{
		if (pmprice != null) {
			{
				if (numbervalidation.test(pmprice)) {
					document.getElementById('center_content:product-reg:prod-market-prize').style.borderColor = "#D3D3D3";
				document.getElementById('mpriceicon').innerHTML="";
				pmprice_st=true;
				} else {
					document.getElementById('center_content:product-reg:prod-market-prize').style.borderColor = "red";
					document.getElementById('mpriceicon').innerHTML="Market price should be numbers";
				}
			}
		}
	}
	/* Unit*/
	if(punit == "select"){
		document.getElementById('center_content:product-reg:prod-unit').style.borderColor = "red";
		document.getElementById('puniticon').innerHTML="Please Choose the Unit";
		
	} else {
			document.getElementById('center_content:product-reg:prod-unit').style.borderColor = "#D3D3D3";
				document.getElementById('puniticon').innerHTML="";
				punit_st=true;
				
	}
	/* vendor*/
	if(pvendor == "select"){
		document.getElementById('center_content:product-reg:vendor-id').style.borderColor = "red";
		document.getElementById('pvendoricon').innerHTML="Please Choose the Vendor Name";
		
	} else {
			document.getElementById('center_content:product-reg:vendor-id').style.borderColor = "#D3D3D3";
				document.getElementById('pvendoricon').innerHTML="";
				pvendor_st=true;
				
	}
	/*brand
	alert("==============9===============");
	if(brand == null || brand == ""){
		document.getElementById('center_content:product-reg:vBrandNo').style.borderColor = "#D3D3D3";
		document.getElementById('pbrandicon').innerHTML="";
		brand_st=true;
	}
	else{
		if(brand != null) {
			{
				if (brandvalidation.test(brand)) {
					document.getElementById('center_content:product-reg:vBrandNo').style.borderColor = "#D3D3D3";
					document.getElementById('pbrandicon').innerHTML="";
					brand_st=true;
				} else {
					document.getElementById('center_content:product-reg:vBrandNo').style.borderColor = "red";
					document.getElementById('pbrandicon').innerHTML="Please Enter valid brand";
				}
			}
		}
	}*/
	/* standard*/
	if(standard == null || standard == ""){
		document.getElementById('center_content:product-reg:prod-standard').style.borderColor = "#D3D3D3";
		document.getElementById('pstandicon').innerHTML="";
		stand_st=true;
	}
	else{
		if(standard != null) {
			{
				if (brandvalidation.test(standard)) {
					document.getElementById('center_content:product-reg:prod-standard').style.borderColor = "#D3D3D3";
					document.getElementById('pstandicon').innerHTML="";
					stand_st=true;
				} else {
					document.getElementById('center_content:product-reg:prod-standard').style.borderColor = "red";
					document.getElementById('pstandicon').innerHTML="Please Enter valid standard";
				}
			}
		}
	}
	/*alert("category"+pcategory_st);
	alert("code"+pcode_st);
	alert("pname"+pname_st);
	alert("punit"+punit_st);
	alert("psize"+psize_st);
	alert("vendor"+pvendor_st);
	alert("actual price"+paprice_st);
	alert("market price"+pmprice_st);*/
	if((pcategory_st == true) && (pcode_st == true) && (pname_st == true) && (punit_st == true )
			 && (pvendor_st == true ) && (paprice_st == true ) && (pmprice_st == true ) && 
			 (stand_st == true)){
		productnamecheck([{name:'param3', value:pname}]);
		productcodecheck([{name:'param4', value:pcode}]);
		document.getElementById('center_content:product-reg:hidenBtn').click();
	}
	
	}catch (err) {
}
}

function checkFail3(){
	var pcode = document.getElementById('center_content:product-reg:vproductLimit').value;
	if(pcode != null || pcode != ""){
		document.getElementById('center_content:product-reg:vproductLimit').style.borderColor = "red";
		document.getElementById('pcodeicon').innerHTML="This Product Code Already Exist.";
	}	
} 

function checkFail2(){
	var pname = document.getElementById('center_content:product-reg:prod-name').value;
	if(pname != null || pname != ""){
		document.getElementById('center_content:product-reg:prod-name').style.borderColor = "red";
		document.getElementById('pnameicon').innerHTML="This Product Name Already Exist.";
	}
} 

function checkSuccess2(){
	var pname = document.getElementById('center_content:product-reg:prod-name').value;
	if(pname != null || pname != ""){
		document.getElementById('center_content:product-reg:prod-name').style.borderColor = "#D3D3D3";
		document.getElementById('pnameicon').innerHTML="";
	}
}
function checkSuccess3(){
	var pcode = document.getElementById('center_content:product-reg:vproductLimit').value;
	if(pcode != null || pcode != ""){
		document.getElementById('center_content:product-reg:vproductLimit').style.borderColor = "#D3D3D3";
		document.getElementById('pcodeicon').innerHTML="";
	}	
}
//prema end 27/04/2016


//prema begin 28/04/2016 reset in product registration

function productclear()
{
	 document.getElementById('center_content:product-reg:vproductLimit').value= "";
	 document.getElementById('center_content:product-reg:vproductLimit').style.borderColor="";
	 document.getElementById('pcodeicon').innerHTML="";
	 document.getElementById('center_content:product-reg:prod-name').value= "";
	 document.getElementById('center_content:product-reg:prod-name').style.borderColor="";
	 document.getElementById('pnameicon').innerHTML="";
	 document.getElementById('center_content:product-reg:prod-size').value= "";
	 document.getElementById('center_content:product-reg:prod-size').style.borderColor="";
	 document.getElementById('psizeicon').innerHTML="";
	 document.getElementById('center_content:product-reg:prod-actual-prize').value= "";
	 document.getElementById('center_content:product-reg:prod-actual-prize').style.borderColor="";
	 document.getElementById('apriceicon').innerHTML="";
	 document.getElementById('center_content:product-reg:prod-market-prize').value= "";
	 document.getElementById('center_content:product-reg:prod-market-prize').style.borderColor="";
	 document.getElementById('mpriceicon').innerHTML="";
	 document.getElementById('center_content:product-reg:vBrandNo').value="";
	 document.getElementById('center_content:product-reg:prod-standard').value="";
	 document.getElementById('center_content:product-reg:prod-desc').value="";
	 document.getElementById('center_content:product-reg:pcate_label').innerHTML = '---Select---';
	 document.getElementById('center_content:product-reg:pcate').style.borderColor="";
	 document.getElementById('pcateicon').innerHTML="";
	 document.getElementById('center_content:product-reg:vendor-id_label').innerHTML = '---Select---';
	 document.getElementById('center_content:product-reg:vendor-id').style.borderColor="";
	 document.getElementById('pvendoricon').innerHTML="";
	 document.getElementById('center_content:product-reg:prod-unit_label').innerHTML = '---Select---';
	 document.getElementById('center_content:product-reg:prod-unit').style.borderColor="";
	 document.getElementById('puniticon').innerHTML="";
	 
}

function vendorValidation1(){
	var phonevalidation =/^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/;
	try {
		var vendor_name = document.getElementById('center_content:ven:vfname').value;
		var telephone = document.getElementById('center_content:ven:vtpno').value;
		var country = document.getElementById('center_content:ven:tabviewid:vcoun').value;
		var email_id = document.getElementById('center_content:ven:vemail').value;
		var vendor_name_st=false;
		var telephone_st=false;
		var country_st=false;
		var email_id_st=false;
		var atpos = email_id.indexOf("@");
	    var dotpos = email_id.lastIndexOf(".");
	    
	    /* Vendor_name  */
		if(vendor_name == null || vendor_name == ""){
			document.getElementById('center_content:ven:vfname').style.borderColor = "red";
			document.getElementById('vfnameicon').innerHTML="Please Enter the Firm Name";
		}else{
			document.getElementById('center_content:ven:vfname').style.borderColor = "#d3d3d3";
			document.getElementById('vfnameicon').innerHTML="";
			vendornamecheck([{name:'param1', value:vendor_name}]);
			vendor_name_st=true;
		}
		
		/* Telephone */
		if(telephone == null || telephone == ""){
			document.getElementById('center_content:ven:vtpno').style.borderColor = "red";
			document.getElementById('vtpnoicon').innerHTML="Please Enter the Telephone Number";
		}else{
			if (telephone != null) {
					if (phonevalidation.test(telephone)) {
						document.getElementById('center_content:ven:vtpno').style.borderColor = "#D3D3D3";
						document.getElementById('vtpnoicon').innerHTML="";
						telephone_st=true;
					}
					else{
						document.getElementById('center_content:ven:vtpno').style.borderColor = "red";
						document.getElementById('vtpnoicon').innerHTML="Please Enter the Valid Telephone Number";
					}
			}
		}
		
		/* Country  */
		if(country == "select"){
			document.getElementById('center_content:ven:tabviewid:vcoun').style.borderColor = "red";
			document.getElementById('vcounicon').innerHTML="Please Choose the Country";
		} else {
				document.getElementById('center_content:ven:tabviewid:vcoun').style.borderColor = "#D3D3D3";
				document.getElementById('vcounicon').innerHTML="";
				country_st=true;
		}
		
		/* Email  */
		if (email_id == null || email_id == "") {
			document.getElementById('center_content:ven:vemail').style.borderColor = "red";
			document.getElementById('vemailicon').innerHTML="Please Enter the Email ID";
		} else {
			if (email_id != null) {
				{
					if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email_id.length) {
						 document.getElementById('center_content:ven:vemail').style.borderColor = "red";
						 document.getElementById('vemailicon').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
						
					} else {
						document.getElementById('center_content:ven:vemail').style.borderColor = "#D3D3D3";
						document.getElementById('vemailicon').innerHTML="";
						email_id_st=true;
					}
				}
			}
		}
		if((vendor_name_st == true) && (telephone_st == true) && (country_st == true) && (email_id_st==true)){
			alert("js");
			vendornamecheck([{name:'param1', value:vendor_name}]);
			document.getElementById('center_content:hidenBtn1').click();
		}
	}catch (err) {
		alert(err);
	}
}

//prema end 28/04/2016

function globalBlockUI(){
	PF('globalblocksUI').show();
	globalsearchblockUI();
}
function custnameclick(){
	remote1();
}
function productnameClick(){
	remote2();
}

function venWebsite(){
	var website_st=false;
	var websitevalidation= /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/|www\.)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;	
	var website = document.getElementById('center_content:venModify:eweb').value;
	if(website == null || website == ""){
		document.getElementById('center_content:venModify:eweb').style.borderColor = "";
		document.getElementById('vwebicon').innerHTML="";
		website_st=true;
	}else if(website != null) {
		if (websitevalidation.test(website)) {
			document.getElementById('center_content:venModify:eweb').style.borderColor = "";
			document.getElementById('vwebicon').innerHTML="";
			website_st=true;
		} else {
			document.getElementById('center_content:venModify:eweb').style.borderColor = "";
			document.getElementById('vwebicon').innerHTML="Please Enter valid Website";
			website_st=false;
		}
	}
	if(website_st == true){
		document.getElementById('center_content:venModify:hidenBtn').click();
	}
}

function crmRegistrationValidation(){ 
	try {
			var namevalidation = /^[a-zA-Z ]{3,30}$/;
			var addressvalidation =/[ A-Za-z0-9_@./#&+-]*$/;
			var emailvalidation = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
			var phonevalidation = /^(\$|)([10-13]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
			var taxvalidation=/^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
			var websitevalidation= /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/|www\.)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
			var customer_name = document.getElementById('center_content:cus:cfname').value;
			var custelephone = document.getElementById('center_content:cus:cusmno').value;
			var date = document.getElementById('center_content:cus:cdate_input').value;
			var cuscity = document.getElementById('center_content:cus:tabviewid:ccity1').value;
			var category = document.getElementById('center_content:cus:ccate').value;
			var status = document.getElementById('center_content:cus:statuss').value;
			var country = document.getElementById('center_content:cus:tabviewid:ccoun1').value;
			var product = document.getElementById('center_content:cus:crmproduct').value;
			var email = document.getElementById('center_content:cus:cemail').value;
			var mail = document.getElementById('center_content:cus:cmail').value;
			var address = document.getElementById('center_content:cus:tabviewid:saddr').value;
			var website=document.getElementById('center_content:cus:web').value;
			var licencenumber=document.getElementById('center_content:cus:tlic').value;
			var taxnumber=document.getElementById('center_content:cus:ctax').value;
			var faxnumber=document.getElementById('center_content:cus:cfax').value;
			
			var atpos = email.indexOf("@");
		    var dotpos = email.lastIndexOf(".");
			var cuscity_st=false;
			var cusdate_st=false;
			var custelephone_st=false;
			var customer_name_st=false;
			var cuscategory_st=false;
			var status_st=false;
			var cuscountry_st=false;
			var cupayment=false;
			var cuday=false;
			var product_st=false;
			var email_st=false;
			var mail_st=false;
			var address_st=false;
			var website_st=false;
			var licencenumber_st=false;
			var tax_st=false;
			var fax_st=false;
			
			if(customer_name == null || customer_name == ""){
				
				document.getElementById('center_content:cus:cfname').style.borderColor = "red";
				document.getElementById('cnameicon').innerHTML="Please Enter Person Name";
			}else{
				if (customer_name != null) {
					{
						if (namevalidation.test(customer_name)) {
							document.getElementById('center_content:cus:cfname').style.borderColor = "#D3D3D3";
							document.getElementById('cnameicon').innerHTML="";
							customer_name_st=true;
						} else {
							document.getElementById('center_content:cus:cfname').style.borderColor = "red";
							document.getElementById('cnameicon').innerHTML="Customer Name should be Alphabet";
						}
					}
				}
			}
			if(custelephone == null || custelephone == ""){
				document.getElementById('center_content:cus:cusmno').style.borderColor = "red";
				document.getElementById('cuspnoicon').innerHTML="Please Enter the Mobile Number";
			}else{
				if (custelephone != null) 
					{
					if (phonevalidation.test(custelephone)) {
								document.getElementById('center_content:cus:cusmno').style.borderColor = "#D3D3D3";
								document.getElementById('cuspnoicon').innerHTML="";
								custelephone_st=true;
						}
					else{
						document.getElementById('center_content:cus:cusmno').style.borderColor = "red";
						document.getElementById('cuspnoicon').innerHTML="Mobile Number with in 13 digit Numbers";
						}
					}
						
			}
			if(date == null || date ==""){
				document.getElementById('center_content:cus:cdate_input').style.borderColor = "red";
				document.getElementById('cdateicon').innerHTML="Please Select the Date";
				
			} else {
					document.getElementById('center_content:cus:cdate_input').style.borderColor = "#D3D3D3";
					document.getElementById('cdateicon').innerHTML="";
					cusdate_st=true;
						
			}
			if( cuscity == "" || cuscity==null){
				document.getElementById('center_content:cus:tabviewid:ccity1').style.borderColor = "red";
				document.getElementById('cuscityicon').innerHTML="Please Enter the City";
			}else{
				if (cuscity != null) {
					{
						if (namevalidation.test(cuscity)) {
							document.getElementById('center_content:cus:tabviewid:ccity1').style.borderColor = "#D3D3D3";
						document.getElementById('cuscityicon').innerHTML="";
						cuscity_st=true;
						} else {
							document.getElementById('center_content:cus:tabviewid:ccity1').style.borderColor = "red";
							document.getElementById('cuscityicon').innerHTML="City should be Alphabet";
						}
					}
				}
			}
			
			if( address == "" || address==null){
				document.getElementById('center_content:cus:tabviewid:saddr').style.borderColor = "red";
				document.getElementById('caddricon').innerHTML="Please Enter the Address";
			}else{
				if (address != null) {
					{
						if (addressvalidation.test(address)) {
							document.getElementById('center_content:cus:tabviewid:saddr').style.borderColor = "#red";
							document.getElementById('caddricon').innerHTML="";
						address_st=true;
						} else {
							document.getElementById('center_content:cus:tabviewid:saddr').style.borderColor = "D3D3D3";
							document.getElementById('caddricon').innerHTML="";
						}
					}
				}
			}
		
			if (email == null || email == "") {
				document.getElementById('center_content:cus:cemail').style.borderColor = "#red";
				document.getElementById('crmemail').innerHTML="Please Enter the email";
				email_st=true;
			} else {
				if (email != null) {
					{
						if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
							document.getElementById('center_content:cus:cemail').style.borderColor = "red";
							document.getElementById('crmemail').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
						} else {
							document.getElementById('center_content:cus:cemail').style.borderColor = "#D3D3D3";
							document.getElementById('crmemail').innerHTML="";
							email_st=true;
						}
					}
				}
			}
			
			if (mail == null || mail == "") {
				document.getElementById('center_content:cus:cmail').style.borderColor = "#red";
				document.getElementById('cma').innerHTML="";
				mail_st=true;
			} else {
				if (mail != null) {
					{
						if (atpos<1 || dotpos<atpos+2 || dotpos+2>=mail.length) {
							document.getElementById('center_content:cus:cmail').style.borderColor = "red";
							document.getElementById('cma').innerHTML="Please Enter the Valid Email ID(Eg:aa@bb.cc)";
						} else {
							document.getElementById('center_content:cus:cmail').style.borderColor = "#D3D3D3";
							document.getElementById('cma').innerHTML="";
							mail_st=true;
						}
					}
				}
			}
			
			if(website == null || website == ""){
				document.getElementById('center_content:cus:web').style.borderColor = "#D3D3D3";
				document.getElementById('cusweb').innerHTML="";
				website_st=true;
			}
			else{
				if(website != null) {
					{
						if (websitevalidation.test(website)) {
							document.getElementById('center_content:cus:web').style.borderColor = "#D3D3D3";
							document.getElementById('cusweb').innerHTML="";
							website_st=true;
						} else {
							document.getElementById('center_content:cus:web').style.borderColor = "red";
							document.getElementById('cusweb').innerHTML="Please Enter valid Website";
						}
					}
				}
			}
			
			if(taxnumber == null || taxnumber == ""){
				document.getElementById('center_content:cus:ctax').style.borderColor = "#D3D3D3";
				document.getElementById('custax').innerHTML="";
				tax_st=true;
			}
			else{
				if(taxnumber != null) {
					{
						if (taxvalidation.test(taxnumber)) {
							document.getElementById('center_content:cus:ctax').style.borderColor = "#D3D3D3";
							document.getElementById('custax').innerHTML="";
							tax_st=true;
						} else {
							document.getElementById('center_content:cus:ctax').style.borderColor = "red";
							document.getElementById('custax').innerHTML="Please Enter valid Tax Number";
						}
					}
				}
			}
			if(faxnumber == null || faxnumber == ""){
				document.getElementById('center_content:cus:cfax').style.borderColor = "#D3D3D3";
				document.getElementById('cusfax').innerHTML="";
				fax_st=true;
			}
			else{
				if(faxnumber != null) {
					{
						if (taxvalidation.test(faxnumber)) {
							document.getElementById('center_content:cus:cfax').style.borderColor = "#D3D3D3";
							document.getElementById('cusfax').innerHTML="";
							fax_st=true;
						} else {
							document.getElementById('center_content:cus:cfax').style.borderColor = "red";
							document.getElementById('cusfax').innerHTML="Please Enter valid Fax Number";
						}
					}
				}
			}
			
			if(licencenumber == null || licencenumber == ""){
				document.getElementById('center_content:cus:tlic').style.borderColor = "#D3D3D3";
				document.getElementById('custrade').innerHTML="";
				licencenumber_st=true;
			}
			else{
				if(licencenumber != null) {
					{
						if (taxvalidation.test(licencenumber)) {
							document.getElementById('center_content:cus:tlic').style.borderColor = "#D3D3D3";
							document.getElementById('custrade').innerHTML="";
							licencenumber_st=true;
						} else {
							document.getElementById('center_content:cus:tlic').style.borderColor = "red";
							document.getElementById('custrade').innerHTML="Please Enter valid Licence Number";
						}
					}
				}
			}
			
			if(category == "select"){
				document.getElementById('center_content:cus:ccate').style.borderColor = "red";
				document.getElementById('ccateicon').innerHTML="Please Select the Industry";
				
			}else {
				document.getElementById('center_content:cus:ccate').style.borderColor = "#D3D3D3";
				document.getElementById('ccateicon').innerHTML="";
				cuscategory_st=true;
			}
			if(status == "select"){
				document.getElementById('center_content:cus:statuss').style.borderColor = "red";
				document.getElementById('cstatuss').innerHTML="Please Select the Status";
				
			}else {
				document.getElementById('center_content:cus:statuss').style.borderColor = "#D3D3D3";
				document.getElementById('cstatuss').innerHTML="";
				status_st=true;
			}
			
			if(product == "select"){
				document.getElementById('center_content:cus:crmproduct').style.borderColor = "red";
				document.getElementById('crmpr').innerHTML="Please Select the Product";
				
			}else {
				document.getElementById('center_content:cus:crmproduct').style.borderColor = "#D3D3D3";
				document.getElementById('crmpr').innerHTML="";
				crmproduct_st=true; 
			}
			
			if(country == "select"){
				document.getElementById('center_content:cus:tabviewid:ccoun1').style.borderColor = "red";
				document.getElementById('ccounicon').innerHTML="Please Select the Country";
				
			} else {
					document.getElementById('center_content:cus:tabviewid:ccoun1').style.borderColor = "#D3D3D3";
						document.getElementById('ccounicon').innerHTML="";
						cuscountry_st=true;
						
			}
			
			if((customer_name_st == true) && (custelephone_st == true) && (cusdate_st == true)  
					 && (cuscity_st == true) && (cuscategory_st==true) && (cuscountry_st==true) && (status_st==true) && (crmproduct_st==true)
					 && (email_st==true) && (address_st==true) && (website_st==true) && (licencenumber_st==true) && (fax_st == true) && (tax_st == true)
					 && (mail_st == true)){   
				
				customernamecheck([{name:'param2', value:customer_name}]);
				document.getElementById('center_content:cus:hidenBtn').click();
				
			}
			
		}catch (err) {
			alert(err);
	}
	}

function vendornewvalidation(){	
	try{
		var phonevalidation =/^(\$|)([10-13]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
		var newdays =  document.getElementById('center_content:form2:neww').value;
		var newdays_st=false;
		
		if(newdays == null || newdays == ""){
			document.getElementById('center_content:form2:neww').style.borderColor = "#D3D3D3";
			document.getElementById('vendaysss').innerHTML="Please Enter NewDay";
			
		}
		else{
			if(newdays != null) {
				{
					if (phonevalidation.test(newdays)) {
						document.getElementById('center_content:form2:neww').style.borderColor = "#D3D3D3";
						document.getElementById('vendaysss').innerHTML="";
						newdays_st=true;
					} else {
						document.getElementById('center_content:form2:neww').style.borderColor = "red";
						document.getElementById('vendaysss').innerHTML="Please Enter Only Number";
					}
				}
			}
		}
		
		if((newdays_st==true)){
			document.getElementById('center_content:form2:hid').click();
			
		}
	}catch(err){
		alert(err);
	}
}

function cusWebsite(){
	var website_st=false;
	var websitevalidation= /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/|www\.)[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;	
	var website = document.getElementById('center_content:cusModify:cuswebsite').value;
	if(website == null || website == ""){
		document.getElementById('center_content:cusModify:cuswebsite').style.borderColor = "";
		document.getElementById('cwebicon').innerHTML="";
		website_st=true;
	}else if(website != null) {
		if (websitevalidation.test(website)) {
			document.getElementById('center_content:cusModify:cuswebsite').style.borderColor = "";
			document.getElementById('cwebicon').innerHTML="";
			website_st=true;
		} else {
			document.getElementById('center_content:cusModify:cuswebsite').style.borderColor = "";
			document.getElementById('cwebicon').innerHTML="Please Enter valid Website";
			website_st=false;
		}
	}
	if(website_st == true){
		document.getElementById('center_content:cusModify:hidenBtn').click();
	}
	
}

//stanley for revenue

function revenue(){	
	try{
		var namevalidation = /^[a-zA-Z ]{3,30}$/;
		var phonevalidation = /^(\$|)([10-13]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
		var newdays =  document.getElementById('center_content:rev:rpgname').value;
		var bill =  document.getElementById('center_content:rev:rbil').value;
		var billper =  document.getElementById('center_content:rev:rbb').value;
		var total =  document.getElementById('center_content:rev:rtot').value;
		var newdays_st=false;var bill_st=false;var billper_st=false;var total_st=false;
		
		if(newdays == null || newdays == ""){
			
			document.getElementById('center_content:rev:rpgname').style.borderColor = "red";
			document.getElementById('pname').innerHTML="Please Enter ProgrammeName";
		}else{
			if (newdays != "") {
				{
					if (namevalidation.test(newdays)) {
						document.getElementById('center_content:rev:rpgname').style.borderColor = "#D3D3D3";
						document.getElementById('pname').innerHTML="";
						newdays_st=true;
					} else {
						document.getElementById('center_content:rev:rpgname').style.borderColor = "red";
						document.getElementById('pname').innerHTML="";
					}
				}
			}
		}
		if(bill == null || bill == ""){
			document.getElementById('center_content:rev:rbil').style.borderColor = "#D3D3D3";
			document.getElementById('bil').innerHTML="";
		}else{
			if (bill != null) 
				{
				if (phonevalidation.test(bill)) {
							document.getElementById('center_content:rev:rbil').style.borderColor = "#D3D3D3";
							document.getElementById('bil').innerHTML="";
							bill_st=true;
					}
				else{
					document.getElementById('center_content:rev:rbil').style.borderColor = "red";
					document.getElementById('bil').innerHTML="Please Enter valid bill Number";
					}
				}
		}
		
		if(billper == null || billper == ""){
			document.getElementById('center_content:rev:rbb').style.borderColor = "#D3D3D3";
			document.getElementById('bilper').innerHTML="";
		}else{
			if (billper != null) 
				{
				if (phonevalidation.test(billper)) {
							document.getElementById('center_content:rev:rbb').style.borderColor = "#D3D3D3";
							document.getElementById('bilper').innerHTML="";
							billper_st=true;
					}
				else{
					document.getElementById('center_content:rev:rbb').style.borderColor = "red";
					document.getElementById('bilper').innerHTML="Please Enter valid Number";
					}
				}
		}
		
		if(total == null || total == ""){
			document.getElementById('center_content:rev:rtot').style.borderColor = "#D3D3D3";
			document.getElementById('tot').innerHTML="";
		}else{
			if (total != null) 
				{
				if (phonevalidation.test(total)) {
							document.getElementById('center_content:rev:rtot').style.borderColor = "#D3D3D3";
							document.getElementById('tot').innerHTML="";
							total_st=true;
					}
				else{
					document.getElementById('center_content:rev:rtot').style.borderColor = "red";
					document.getElementById('tot').innerHTML="Please Enter valid Number";
					}
				}
		}
		
		if((newdays_st == true)){   
			document.getElementById('center_content:rev:hid').click();
		}
		
	}catch(err){
		alert(err);
	}
}
 
function revenueUpdatevalidation(){	
	try{
		var namevalidation = /^[a-zA-Z ]{3,30}$/;
		var phonevalidation = /^(\$|)([10-13]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
		var newdays =  document.getElementById('center_content:revedit:rpgname1').value;
		var bill =  document.getElementById('center_content:revedit:rbil1').value;
		var billper =  document.getElementById('center_content:revedit:rbb1').value;
		var total =  document.getElementById('center_content:revedit:rtot1').value;
		var newdays_st=false;var bill_st=false;var billper_st=false;var total_st=false;
		
		if(newdays == null || newdays == ""){
			
			document.getElementById('center_content:revedit:rpgname1').style.borderColor = "red";
			document.getElementById('pname1').innerHTML="Please Enter ProgrammeName";
		}else{
			if (newdays != "") {
				{
					if (namevalidation.test(newdays)) {
						document.getElementById('center_content:revedit:rpgname1').style.borderColor = "#D3D3D3";
						document.getElementById('pname1').innerHTML="";
						newdays_st=true;
					} else {
						document.getElementById('center_content:revedit:rpgname1').style.borderColor = "#D3D3D3";
						document.getElementById('pname1').innerHTML="";
					}
				}
			}
		}
		if(bill == null || bill == ""){
			document.getElementById('center_content:revedit:rbil1').style.borderColor = "#D3D3D3";
			document.getElementById('bil1').innerHTML="";
		}else{
			if (bill != null) 
				{
				if (phonevalidation.test(bill)) {
							document.getElementById('center_content:revedit:rbil1').style.borderColor = "#D3D3D3";
							document.getElementById('bil1').innerHTML="";
							bill_st=true;
					}
				else{
					document.getElementById('center_content:revedit:rbil1').style.borderColor = "red";
					document.getElementById('bil1').innerHTML="Please Enter valid bill Number";
					}
				}
		}
		
		if(billper == null || billper == ""){
			document.getElementById('center_content:revedit:rbb1').style.borderColor = "#D3D3D3";
			document.getElementById('bilper1').innerHTML="";
		}else{
			if (billper != null) 
				{
				if (phonevalidation.test(billper)) {
							document.getElementById('center_content:revedit:rbb1').style.borderColor = "#D3D3D3";
							document.getElementById('bilper1').innerHTML="";
							billper_st=true;
					}
				else{
					document.getElementById('center_content:revedit:rbb1').style.borderColor = "red";
					document.getElementById('bilper1').innerHTML="Please Enter valid Number";
					}
				}
		}
		
		if(total == null || total == ""){
			document.getElementById('center_content:revedit:rtot1').style.borderColor = "#D3D3D3";
			document.getElementById('tot1').innerHTML="";
		}else{
			if (total != null) 
				{
				if (phonevalidation.test(total)) {
							document.getElementById('center_content:revedit:rtot1').style.borderColor = "#D3D3D3";
							document.getElementById('tot1').innerHTML="";
							total_st=true;
					}
				else{
					document.getElementById('center_content:revedit:rtot1').style.borderColor = "red";
					document.getElementById('tot1').innerHTML="Please Enter valid Number";
					}
				}
		}
		
		if((newdays_st == true)){   
			document.getElementById('center_content:revedit:updatehid').click();
		}
		
	}catch(err){
		alert(err);
	}
}
