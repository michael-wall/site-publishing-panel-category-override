## Introduction ##
- This 'proof of concept' is designed for customers with Staging enabled, to override the default behavior of the 'PublishingPanelCategory' to control when the Publishing menu item is shown in the Site Administration Menu.
- The default behavior is as follows:
  - When a Site Member has 'Asset Library Settings > Asset Library Entry: View Site and Asset Library Administration Menu' and 'Site Settings > Site: Publish Staging' permissions they will see the Site Administration Menu and within it the Publishing category.
  - If the 'Asset Library Settings > Asset Library Entry: View Site and Asset Library Administration Menu' permission is removed then the Site Administration Menu is not shown.
  - The above is expected behaviour.
- This custom module will change this behavior by replacing the PublishingPanelCategory with a custom implmentation that only shows the Publishing category if the user meets the following criteria:
  - The user is an Administrator or Omni Administrator in the environment.
  - The user is a Site Administrator of the Site in the environment.
  - The user is a Site Owner of the Site in the environment.
- If additional roles (either Regular or Site etc.) should also see the Publishing category then the code in the CustomPublishingPanelCategory.java isShow method should be updated to include additional role check(s).

## Intended Usage ##
- The intended usage is for a Staging enabled system where a customer wants users to have access to the 'Publish to Live' action from within widgets such as 'Documents & Media' and 'Web Content' but doesn't want users seeing the 'Publishing' category within the Site Adminsitration.
- The custom module is not a security fix, it is a workaround to hide the Publishing category only.
-  Users with 'Site Settings > Site: Publish Staging' permission can still 'Publish to Live' from within the widgets etc.

## Setup ##
- Blacklist the out of the box PublishingPanelCategory component in the live and staging environments:
  - System Settings > Platform > Module Container > Blacklist Component:
  - com.liferay.product.navigation.site.administration.internal.application.list.PublishingPanelCategory
- Build and deploy the custom OSGi module to all nodes in the live and staging environments:
  - custom-publishing-panel-category / com.mw.custom.publishing.panel.category-1.0.0.jar

## Notes ##
- This is a ‘proof of concept’ that is being provided ‘as is’ without any support coverage or warranty.
- The implementation was tested locally using Liferay DXP 2025.Q1.19 LTS with Local Live Staging.
  - Ensure it is fully tested in a non-production environment with Remote Live Staging before considering depploying to production.
- The custom OSGi module must be deployed to all nodes in the live and staging environment clusters.
- The implementation uses a custom OSGi module meaning it is compatible with Liferay DXP Self-Hosted and Liferay PaaS, but is not compatible with Liferay SaaS.
- JDK 21 is expected for both compile time and runtime.
