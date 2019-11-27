document.write("<script src=\"//translate.google.cn/translate_a/element.js?cb=googleTranslateElementInit\"></script>")


function googleTranslateElementInit() {

    new google.translate.TranslateElement(
        {
            //pageLanguage: 'en',
            layout: google.translate.TranslateElement.InlineLayout.SIMPLE
        },
        'google_translate_element'
    );

}
