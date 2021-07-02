package com.gousade.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

public class HasAnyPermissionsTag extends PermissionTag {
    private static final long serialVersionUID = 1L;
    private static final String PERMISSION_NAMES_DELIMITER = ",";

    public HasAnyPermissionsTag() {
    }

    @Override
    protected boolean showTagBody(String permissions) {
        boolean hasAnyPermissions = false;
        Subject subject = getSubject();
        if (subject != null) {
            for (String permission : permissions.split(PERMISSION_NAMES_DELIMITER)) {
                if (subject.isPermitted(permission.trim())) {
                    hasAnyPermissions = true;
                    break;
                }
            }
        }
        return hasAnyPermissions;
    }
}
