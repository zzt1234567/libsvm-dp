package org.shirdrn.document.preprocessing.component.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shirdrn.document.preprocessing.api.Context;
import org.shirdrn.document.preprocessing.api.FeatureTermSelector;
import org.shirdrn.document.preprocessing.api.constants.ConfigKeys;
import org.shirdrn.document.preprocessing.common.AbstractComponent;
import org.shirdrn.document.preprocessing.utils.ReflectionUtils;

/**
 * Load term and label vector files generated during TRAIN phase, and then
 * we use {@linkplain TestDocumentPreprocessingDriver} to process TEST data for
 * fitting libSVM.
 *  
 * @author Shirdrn
 */
public class LoadFeatureTermVector extends AbstractComponent {

	private static final Log LOG = LogFactory.getLog(LoadFeatureTermVector.class);
	private final FeatureTermSelector featuredTermsSelector;
	
	public LoadFeatureTermVector(final Context context) {
		super(context);
		String selectorClazz = context.getConfiguration().get(
				ConfigKeys.FEATURE_VECTOR_SELECTOR_CLASS, 
				"org.shirdrn.document.preprocessing.measure.chi.ChiFeatureTermSelector");
		LOG.info("Feature term vector selector: selectorClazz=" + selectorClazz);
		featuredTermsSelector = ReflectionUtils.newInstance(selectorClazz, FeatureTermSelector.class);
	}
	
	@Override
	public void fire() {
		// load term vector
		featuredTermsSelector.load(context);
		LOG.info("Feature term vector loaded.");
	}
	
}
