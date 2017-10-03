
function log(message) {
    if (typeof console != 'undefined') {
        console.log(message);
    }
}


/**
 * detect VCCD activeX install
 * returns true or false
 */
function VCCDorNull() {

    vccdRedirectCallback = null;

    try {
        vccdRedirectCallback = new ActiveXObject('VccDesktopApi.VccdCallControl');
    } catch (e) {
        // catch exception
    }

    if (vccdRedirectCallback) {
        // ActiveX is installed
        return true;
    }
    return false;
}


/**
 * detect IE
 * returns version of IE or false, if browser is not Internet Explorer
 */
function isIE() {
    return navigator.userAgent.indexOf("MSIE ") > -1 || navigator.userAgent.indexOf("Trident/") > -1 || navigator.userAgent.indexOf("Edge/") > -1;
}


/**
 * format the VCCD html
 * returns the html to take the place of the telephone number
 */
function setupVccd(sourceText, redirectType, redirectMsg) {
    var newHtml;
    var strRedirectType = redirectType;

    // if IE8 or greater, the style sheet will support hover, so no javascript is needed
    newHtml = "<span class=\"vccdr_background\" onmouseover=\"this.className += '_hover';\" onmouseout=\"this.className = this.className.replace('_hover', '');\" ";
    newHtml += "title=\"Redirect the current call (or outdial) to: ";
    newHtml += sourceText;
    newHtml += ", with the VCC Desktop.\">";
    newHtml += "<span class=\"vccdr_left_span\">&nbsp;</span>";
    newHtml += "<span class=\"vccdr_text_span\" ";
    newHtml += "onclick=\"if (vccdRedirectCallback){vccdRedirectCallback.StartRedirect(";
    newHtml += strRedirectType;
    newHtml += ", '";
    newHtml += sourceText;
    newHtml += "','";
    newHtml += redirectMsg;
    newHtml += "', 0);}; \" >";
    newHtml += sourceText;
    newHtml += "</span>"; // end for number
    newHtml += "<span class=\"vccdr_dropdown_span\" ";
    newHtml += "onclick=\"if (vccdRedirectCallback){vccdRedirectCallback.StartRedirect(";
    newHtml += strRedirectType;
    newHtml += ", '";
    newHtml += sourceText;
    newHtml += "','";
    newHtml += redirectMsg;
    newHtml += "', 1);}; \" >";
    newHtml += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    newHtml += "</span>"; // end for dropdown
    newHtml += "</span>"; // end for background
    newHtml += "</span>"; // end for container

    return newHtml;

}


 
$.fn.addVccdScript = function() {
    var newHtml = '';

    newHtml = "<script>";
    newHtml += "var vccdRedirectCallback;\n";
    newHtml += "function callVccdRedirectCallback(type, number, description, option) {\n";
    newHtml += "  if (!vccdRedirectCallback) \n";
    newHtml += "    vccdRedirectCallback = new ActiveXObject(\"VccDesktopApi.VccdCallControl\");\n";
    newHtml += "  vccdRedirectCallback.StartRedirect(type, number, description, option);\n";
    newHtml += "}\n";
    newHtml += "</script>";

    return newHtml;

}


/**
 * Check string to see if it's a valid phone number
 * takes and returns the data structure
 * 
 * var data = {
 *   text: '(612) 991-8580 CR',
 *   startingPosition: 0,
 *   endingPosition: 0,
 *   numberLength: 10,
 *   redirectOption: '',
 *   result: false
 * };
 **/
function isAPhoneNumber(data) {

    var justDigits = "1234567890";
    var leadingBlank = false;
    var leadingLeftParen = false;
    var leadingHyphen = false;
    var leadingPeriod = false;
    var leadingDigit = false;

    var redirectionTypes = {
        None: 0,
        CarrierRedirect: 1,
        WarmTransfer: 2,
        ColdTransfer: 3,
        WarmConference: 4,
        ColdConference: 5,
        Referral: 6,
        StandardDial: 7
    };

    if (typeof data != 'undefined' && data != null && data != '') {

        var nextCharIndex = data.startingPosition;
        var textToCheck = data.text;
        var curChar = ' ';
        var nextChar = ' ';

        // check for leading symbols
        var bOkSoFar = false;
        if (nextCharIndex + 1 < textToCheck.length) {
            bOkSoFar = true;
            curChar = textToCheck.charAt(nextCharIndex);
            nextChar = textToCheck.charAt(nextCharIndex + 1);
            switch (curChar) {
                case ' ':
                    leadingBlank = true;
                    if (nextChar == '(') {
                        leadingLeftParen = true;
                        nextCharIndex += 2;
                    } else
                        nextCharIndex += 1;
                    break;
                case '(':
                    leadingLeftParen = true;
                    nextCharIndex += 1;
                    break;
                case '-':
                    leadingHyphen = true;
                    nextCharIndex += 1;
                    break;
                case '.':
                    leadingPeriod = true;
                    nextCharIndex += 1;
                    break;
                default:
                    if (justDigits.search(curChar) > -1)
                        leadingDigit = true;
                    else
                        bOkSoFar = false;
                    break;
            }
        }


        if (!bOkSoFar) {
            data.endingPosition = nextCharIndex + 1;
            data.result = false;
            return data;
        }

        // check for three digits
        bOkSoFar = false;
        if (nextCharIndex + 2 < textToCheck.length) {
            nextChar = textToCheck.charAt(nextCharIndex);
            if (justDigits.search(nextChar) > -1) {
                nextCharIndex += 1;
                nextChar = textToCheck.charAt(nextCharIndex);
                if (justDigits.search(nextChar) > -1) {
                    nextCharIndex += 1;
                    nextChar = textToCheck.charAt(nextCharIndex);
                    if (justDigits.search(nextChar) > -1) {
                        nextCharIndex += 1;
                        bOkSoFar = true;
                    }
                }
            }
        } else {
            nextCharIndex += 1;
        }

        if (!bOkSoFar) {
            data.endingPosition = nextCharIndex;
            data.result = false;
            return data;
        }

        // check for separator symbols
        if (nextCharIndex + 1 < textToCheck.length) {
            curChar = textToCheck.charAt(nextCharIndex);
            nextChar = textToCheck.charAt(nextCharIndex + 1);
            switch (curChar) {
                case ')':
                    if (leadingLeftParen) {
                        if (leadingBlank)
                            if (nextChar == ' ')
                                nextCharIndex += 2;
                            else
                                bOkSoFar = false;
                        else
                        if (nextChar == ' ')
                            nextCharIndex += 2;
                        else
                            nextCharIndex += 1;
                    } else
                        bOkSoFar = false;
                    break;
                case ' ':
                    if (leadingBlank || leadingDigit) {
                        leadingBlank = true;
                        nextCharIndex += 1;
                    } else
                        bOkSoFar = false;
                    break;
                case '-':
                    if (leadingHyphen || leadingDigit) {
                        leadingHyphen = true;
                        nextCharIndex += 1;
                    } else
                        bOkSoFar = false;
                    break;
                case '/': // special case for UHG phone book, treat '/' as hypen
                    if (leadingHyphen || leadingDigit) {
                        leadingHyphen = true;
                        nextCharIndex += 1;
                    } else
                        bOkSoFar = false;
                    break;
                case '.':
                    if (leadingPeriod || leadingDigit) {
                        leadingPeriod = true;
                        nextCharIndex += 1;
                    } else
                        bOkSoFar = false;
                    break;
                default:
                    bOkSoFar = false;
                    break;
            }
        }

        if (!bOkSoFar) {
            data.endingPosition = nextCharIndex;
            data.result = false;
            return data;
        }

        // check for three more digits
        bOkSoFar = false;
        if (nextCharIndex + 2 < textToCheck.length) {
            nextChar = textToCheck.charAt(nextCharIndex);
            if (justDigits.search(nextChar) > -1) {
                nextCharIndex += 1;
                nextChar = textToCheck.charAt(nextCharIndex);
                if (justDigits.search(nextChar) > -1) {
                    nextCharIndex += 1;
                    nextChar = textToCheck.charAt(nextCharIndex);
                    if (justDigits.search(nextChar) > -1) {
                        nextCharIndex += 1;
                        bOkSoFar = true;
                    }
                }
            }
        }

        if (!bOkSoFar) {
            data.endingPosition = nextCharIndex;
            data.result = false;
            return data;
        }

        // check for separator symbol
        if (nextCharIndex < textToCheck.length) {
            curChar = textToCheck.charAt(nextCharIndex);
            nextChar = textToCheck.charAt(nextCharIndex + 1);

            switch (curChar) {
                case ' ':
                    if (leadingBlank || leadingLeftParen)
                        nextCharIndex += 1;
                    else
                        bOkSoFar = false;
                    break;
                case '-':
                    if (leadingHyphen || leadingLeftParen)
                        nextCharIndex += 1;
                    else
                        bOkSoFar = false;
                    break;
                case '.':
                    if (leadingPeriod || leadingLeftParen)
                        nextCharIndex += 1;
                    else
                        bOkSoFar = false;
                    break;
                default:
                    bOkSoFar = false;
                    break;
            }
        }

        if (!bOkSoFar) {
            data.endingPosition = nextCharIndex;
            data.result = false;
            return data;
        }

        // check for four more digits
        bOkSoFar = false;
        if (nextCharIndex + 3 < textToCheck.length) {
            nextChar = textToCheck.charAt(nextCharIndex);
            if (justDigits.search(nextChar) > -1) {
                nextCharIndex += 1;
                nextChar = textToCheck[nextCharIndex];
                if (justDigits.search(nextChar) > -1) {
                    nextCharIndex += 1;
                    nextChar = textToCheck[nextCharIndex];
                    if (justDigits.search(nextChar) > -1) {
                        nextCharIndex += 1;
                        nextChar = textToCheck[nextCharIndex];
                        if (justDigits.search(nextChar) > -1) {
                            nextCharIndex += 1;
                            bOkSoFar = true;
                        }
                    }
                }
            }
        }

        data.endingPosition = nextCharIndex;

        if (!bOkSoFar) {
            data.result = false;
            return data;
        }

        data.redirectOption = -1;
        data.numberLength = nextCharIndex - data.startingPosition;

        // check for trailing digits
        if (nextCharIndex < textToCheck.length) {
            curChar = textToCheck.charAt(nextCharIndex);
            if (justDigits.search(curChar) > -1) {
                data.result = false;
                return data;
            }

            // check for trailing redirect option
            if (nextCharIndex + 2 < textToCheck.length && curChar == ' ') {
                if (textToCheck.charAt(nextCharIndex + 1) == 'W' && textToCheck.charAt(nextCharIndex + 2) == 'T')
                    data.redirectOption = redirectionTypes.WarmTransfer;
                else if (textToCheck.charAt(nextCharIndex + 1) == 'W' && textToCheck.charAt(nextCharIndex + 2) == 'C')
                    data.redirectOption = redirectionTypes.WarmConference;
                else if (textToCheck.charAt(nextCharIndex + 1) == 'C' && textToCheck.charAt(nextCharIndex + 2) == 'T')
                    data.redirectOption = redirectionTypes.ColdTransfer;
                else if (textToCheck.charAt(nextCharIndex + 1) == 'C' && textToCheck.charAt(nextCharIndex + 2) == 'C')
                    data.redirectOption = redirectionTypes.ColdConference;
                else if (textToCheck.charAt(nextCharIndex + 1) == 'C' && textToCheck.charAt(nextCharIndex + 2) == 'R')
                    data.redirectOption = redirectionTypes.CarrierRedirect;
                else if (textToCheck.charAt(nextCharIndex + 1) == 'S' && textToCheck.charAt(nextCharIndex + 2) == 'D')
                    data.redirectOption = redirectionTypes.StandardDial;
            }
        }

        if (data.redirectOption != -1) // option found, need to strip it out
            data.endingPosition += 3;

        data.result = true;

    }

    return data;
}

function findPhoneNumber(data) {

    var indexOfPhone = -1;
    var remainingText = data;
    var startPosition = 0;
    var nextPosition = 0;
    var replacementText = '';

    /*
     * The regex should match telephone #'s with a vaild transfer code ( WT| WC| CT| CC| CR| SD) at the end
     * 812-991-8581 WT
     * 91-612-991-8581 CR
     * (612)991-8581 CR
     * 612/991 8581 SD
     * 555.123.4567 SD
     * 5551234567 SD
     */
    var re = /(\*8|91|1|\*8 |91 |1 )?(\*|\(|\.|-)?\d{3}(\)| |-|\/|\.)+\d{3}(|\.| |-)+\d{4}( WT| WC| CT| CC| CR| SD)/g;

    startPosition = remainingText.search(re);

    while (startPosition > -1 && startPosition + 1 < remainingText.length) {

        var firstChar = remainingText.charAt(startPosition);
        var nextChar = remainingText.charAt(startPosition + 1);
        var newText = '';
        var bReplacedANumber = false;

        log('SearchText: ' + remainingText.substring(startPosition));

        if (firstChar == '*' && nextChar == '8') { // could be start of Carrier Redirect
            nextPosition = startPosition + 1;
        } else if (firstChar == '9' && nextChar == '1') { // could be a 91 and nnnnnnnnnn, or a 91 area code
            nextPosition = startPosition + 1;
        } else if (firstChar == '1') { // strip the 1, and check
            var myPhoneNumber = {
                text: remainingText,
                startingPosition: startPosition + 1,
                endingPosition: 0,
                numberLength: 0,
                redirectOption: '',
                result: false
            };

            myPhoneNumber = isAPhoneNumber(myPhoneNumber);

            log('myPhoneNumber.text: ' + myPhoneNumber.text)
            log('myPhoneNumber.startingPosition: ' + myPhoneNumber.startingPosition)
            log('myPhoneNumber.endingPosition: ' + myPhoneNumber.endingPosition)
            log('myPhoneNumber.numberLength: ' + myPhoneNumber.numberLength)
            log('myPhoneNumber.redirectOption: ' + myPhoneNumber.redirectOption)
            log('myPhoneNumber.result: ' + myPhoneNumber.result)

            if (myPhoneNumber.result) {
                //nextPosition is endPostition of myPhoneNumber object
                nextPosition = myPhoneNumber.endingPosition;
                var parsedNumber = remainingText.substring(myPhoneNumber.startingPosition, myPhoneNumber.startingPosition + myPhoneNumber.numberLength);
                newText = setupVccd(parsedNumber, myPhoneNumber.redirectOption, 'From Knowledge Central');
                log('Found Phone Number: ' + parsedNumber + ' redirectionOption: ' + myPhoneNumber.redirectOption)
                log('newTextfor Phone Number: ' + newText);
                bReplacedANumber = true;
            } else {
                nextPosition = startPosition + 1;
            }

        } else { // just check what we have
            var myPhoneNumber = {
                text: remainingText,
                startingPosition: startPosition,
                endingPosition: 0,
                numberLength: 0,
                redirectOption: '',
                result: false
            };

            myPhoneNumber = isAPhoneNumber(myPhoneNumber);

            log('myPhoneNumber.text: ' + myPhoneNumber.text)
            log('myPhoneNumber.startingPosition: ' + myPhoneNumber.startingPosition)
            log('myPhoneNumber.endingPosition: ' + myPhoneNumber.endingPosition)
            log('myPhoneNumber.numberLength: ' + myPhoneNumber.numberLength)
            log('myPhoneNumber.redirectOption: ' + myPhoneNumber.redirectOption)
            log('myPhoneNumber.result: ' + myPhoneNumber.result)

            if (myPhoneNumber.result) {
                //nextPosition is endPostition of myPhoneNumber object
                nextPosition = myPhoneNumber.endingPosition;
                var parsedNumber = remainingText.substring(myPhoneNumber.startingPosition, myPhoneNumber.startingPosition + myPhoneNumber.numberLength);
                newText = setupVccd(parsedNumber, myPhoneNumber.redirectOption, 'From Knowledge Central');
                log('Found Phone Number: ' + parsedNumber + ' redirectionOption: ' + myPhoneNumber.redirectOption)
                log('Phone Number newText: ' + newText);
                bReplacedANumber = true;
            } else {
                nextPosition = startPosition + 1;
            }
        }

        if (newText.length == 0) {
            replacementText += remainingText.substring(0, nextPosition);
        } else {
            replacementText += remainingText.substring(0, startPosition);
            replacementText += newText;
        }

        remainingText = remainingText.substring(nextPosition);
        startPosition = remainingText.search(re);
    }

    if (bReplacedANumber) {
        replacementText += remainingText;
    }
    
    log('findPhoneNumber: ' + replacementText);
    return replacementText;
}