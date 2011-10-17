<?php
class Tabs {

    public static function viewInTab($pkg, $call, $id, $linkText, $tabTitle) {
        return '<a href="" ' .
        'onclick="javascript:updateTab(\'ajax.php? ' .
        'pkg=' . $pkg .
        '&call=' . $call .
        '&id=' . $id .
        '\', \'' . $tabTitle . '\'); return false;">' . $linkText . '</a>';
    
    }
    
    public static function viewInNewTab($pkg, $call, $id, $linkText, $tabTitle) {
        return '<a href="" ' .
        'onclick="' . Tabs::viewInNewTabJs($action, $object, $id, $tabTitle) . ' return false;">' . $linkText . '</a>';
    
    }
    
    public static function viewInNewTabJs($pkg, $call, $id, $tabTitle) {
        return 'javascript:newTab(\'ajax.php? ' .
        'pkg=' . $pkg .
        '&call=' . $call .
        '&id=' . $id .
        '\', \'' . $tabTitle . '\');';
    
    }

}
?>