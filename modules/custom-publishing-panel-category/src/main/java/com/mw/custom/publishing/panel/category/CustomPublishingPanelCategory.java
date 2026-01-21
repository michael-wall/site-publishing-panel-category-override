package com.mw.custom.publishing.panel.category;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		property = {
			"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION,
			"panel.category.order:Integer=900"
		},
		service = PanelCategory.class
	)
	public class CustomPublishingPanelCategory extends BasePanelCategory {
	
	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		boolean isShow = super.isShow(permissionChecker, group);
		
		if (!isShow) return false; // User doesn't have access
		
		long companyId = group.getCompanyId();
		
		// Omni Administrator
		if (permissionChecker.isOmniadmin()) return true;
		
		// Administrator
		if (permissionChecker.isCompanyAdmin(companyId)) return true;
		
		//Site Administrator
		if (permissionChecker.isGroupAdmin(group.getGroupId())) return true;
		
		// Site Owner
		if (permissionChecker.isGroupOwner(group.getGroupId())) return true;
				
		return false;
	}

	@Override
	public String getKey() {
		return PanelCategoryKeys.SITE_ADMINISTRATION_PUBLISHING;
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "category.site_administration.publishing");
	}

	@Reference
	private Language _language;

	
	private static final com.liferay.portal.kernel.log.Log _log = LogFactoryUtil.getLog(CustomPublishingPanelCategory.class);
}