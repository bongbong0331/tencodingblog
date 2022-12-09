/**
 * 
 */
 
 $(document).ready(function(){
	 
	 $('button').dblclick(function(){
//$('p').hide();
//$("div").animate({left: '250px'});
$("div").animate({
      left: '250px',
      opacity: '0.5',
      height: '150px',
      width: '150px'
    });
    
    $("div").animate({
      left: '250px',
      height: '+=150px',
      width: '+=150px'
    });


	 });
 }); 