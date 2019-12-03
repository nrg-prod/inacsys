(function($) {
  "use strict";

/*<!-- Menu and tiles actions --> */ 
$('.back').click(function() {
    $('.on').addClass('off');
    $('.back').css('opacity','0');
    $('.off').removeClass('on').delay(500).queue(function(next){
        $(this).addClass('display-none');
        next();
    });
    $('.home').removeClass('off').delay(0).queue(function(next){
        $(this).addClass('on').removeClass('display-none');
        next();
    });
});
    
$('.profile-link-box').click(function() {
    $('.profile-link').addClass('active');
    $('.on').addClass('off');
    $('.off').removeClass('on').delay(500).queue(function(next){
        $(this).addClass('display-none');
        $('.back').css('opacity','1');
        next();
    });
    $('.profile').removeClass('off').delay(0).queue(function(next){
        $(this).addClass('on').removeClass('display-none');
        next();
    });
});
    
$('.portfolio-link-box').click(function() {
    $('.portfolio-link').addClass('active');
    $('.on').addClass('off');
    $('.off').removeClass('on').delay(500).queue(function(next){
        $(this).addClass('display-none');
        $('.back').css('opacity','1');
        next();
    });
    $('.portfolio').removeClass('off').delay(0).queue(function(next){
        $(this).addClass('on').removeClass('display-none');
        next();
    });
});
    
$('.education-link-box').click(function() {
    $('.education-link').addClass('active');
    $('.on').addClass('off');
    $('.off').removeClass('on').delay(500).queue(function(next){
        $(this).addClass('display-none');
        $('.back').css('opacity','1');
        next();
    });
    $('.education').removeClass('off').delay(0).queue(function(next){
        $(this).addClass('on').removeClass('display-none');
        next();
    });
});

$('.experience-link-box').click(function() {
    $('.experience-link').addClass('active');
    $('.on').addClass('off');
    $('.off').removeClass('on').delay(500).queue(function(next){
        $(this).addClass('display-none');
        $('.back').css('opacity','1');
        next();
    });
    $('.experience').removeClass('off').delay(0).queue(function(next){
        $(this).addClass('on').removeClass('display-none');
        next();
    });
});

$('.contact-link-box').click(function() {
    $('.contact-link').addClass('active');
    $('.on').addClass('off');
    $('.off').removeClass('on').delay(500).queue(function(next){
        $(this).addClass('display-none');
        $('.back').css('opacity','1');
        next();
    });
    $('.contact').removeClass('off').delay(0).queue(function(next){
        $(this).addClass('on').removeClass('display-none');
        next();
    });
});

/*<!-- Tooltip -->  */
$('[data-toggle="tooltip"]').tooltip('hide');


/*<!-- Scroll -->  */
$(window).load(function(){
    $('.scroll').mCustomScrollbar();
});

$('.scroll').mCustomScrollbar({
    advanced:{
        updateOnContentResize:true
    }
});

/*<!-- Scroll -->  */
$(window).load(function(){
    $('.box-twitter').mCustomScrollbar();
});

$('.box-twitter').mCustomScrollbar({
    advanced:{
        updateOnContentResize:false
    }
});

  // Author code here
})(jQuery);