package com.infy.icci.manager;

import com.infy.icci.service.SchemeService;
import com.infy.icci.to.CardTO;
import com.infy.icci.to.SchemeTO;

public class SchemeManager 
{
	/**
	 * 
	 * Description: This method calls to getSchemeDetails
	 * 				in the SchemeService class
	 * Modified by: Mateo Vidal
	 * @param schemeTo
	 * @return schemeTo
	 * @throws Exception 
	 */

	public SchemeTO getSchemeDetails(SchemeTO schemeTo) throws Exception
	{
		return new SchemeService().getSchemeDetails(schemeTo);
	}
	
	/**
	 * Description: This method calls to updateSchemeDetails
	 * 				in the SchemeService class
	 * Modified by: Mateo Vidal
	 * @param to
	 */
	public void updateSchemeDetails(CardTO to)
	{
		new SchemeService().updateSchemeDetails(to);
	}

}
