/* Copyright 2011 - 88 Creative Pty Ltd. 
 * Copyright of this program is the property of 88 Creative, 
 * without whose written permission reproduction in
 * whole or in part is prohibited. All rights reserved.
 * http://www.inform8.com
 * http://www.88creative.com.au
 */

/**
 * Generic UI functions
 * 
 */

function newClearLeft() {
  return $('<div />').css( {
    "clear" : 'left'
  });
}

function newWrapper() {
  return $('<li />').addClass("jack-field-wrapper");
}

function newLabel(theTable, theFieldDefinition, edit) {
  var theLabel = $('<div />').attr('class', 'jack-createviewupdate-label').html(getLanguageEntry("Tbl_" + theTable.name + "_" + theFieldDefinition.name));
  if (edit) {
    if ($.inArray('notnull', theFieldDefinition.displaySettings.labels) >= 0 && $.inArray('pk', theFieldDefinition.displaySettings.labels) < 0) {
      $('<span />').addClass('jack-ui-validation-requiredfield').html(' *').appendTo(theLabel);
    }
  }
  var langEntry = "Tbl_" + theTable.name + "_" + theFieldDefinition.name + "__help";
  var theHelp = getLanguageEntry(langEntry);
  if (theHelp != langEntry && theHelp != '') {
    theLabel.attr('title', theHelp);
    theLabel.hoverbox();
  }

  return theLabel;
}

function newFieldWrapper(theFieldDefinition) {
  return $('<div />').attr('class', 'jack-createviewupdate-field');
}

function newIdLabel(theTable, theFieldDefinition, value) {
  var daField = $('<div />').text(value);
  return newEditFieldBlock(theTable, theFieldDefinition, daField);
}