package org.eclipse.mylyn.targetprocess.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class TargetProcessImages {

	private static ImageRegistry imageRegistry;

	private static final String T_TOOL = "etool16";
	private static final String T_VIEW = "eview16"; //$NON-NLS-1$

	private static final URL baseURL = TargetProcessUiPlugin.getDefault().getBundle().getEntry("/icons/"); //$NON-NLS-1$

	public static final ImageDescriptor OVERLAY_BUG = create(T_VIEW, "overlay-bug.gif"); //$NON-NLS-1$
	public static final ImageDescriptor OVERLAY_STORY = create(T_VIEW, "overlay-story.gif"); //$NON-NLS-1$
	public static final ImageDescriptor OVERLAY_TASK = create(T_VIEW, "overlay-task.gif"); //$NON-NLS-1$
	public static final ImageDescriptor OVERLAY_REQUEST = create(T_VIEW, "overlay-request.gif"); //$NON-NLS-1$

	public static final ImageDescriptor EDIT = create(T_TOOL, "edit.gif");
	public static final ImageDescriptor PART_MAXIMIZE = create(T_TOOL, "maximize.png");

	public static ImageDescriptor create(String prefix, String name) {
		try {
			return ImageDescriptor.createFromURL(makeIconFileURL(prefix, name));
		} catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	private static URL makeIconFileURL(String prefix, String name) throws MalformedURLException {
		if (baseURL == null) {
			throw new MalformedURLException();
		}

		return new URL(baseURL, prefix + '/' + name);
	}

	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}

		return imageRegistry;
	}

	/**
	 * Lazily initializes image map.
	 */
	public static Image getImage(ImageDescriptor imageDescriptor) {
		ImageRegistry imageRegistry = getImageRegistry();

		Image image = imageRegistry.get("" + imageDescriptor.hashCode()); //$NON-NLS-1$
		if (image == null) {
			image = imageDescriptor.createImage();
			imageRegistry.put("" + imageDescriptor.hashCode(), image); //$NON-NLS-1$
		}
		return image;
	}
}
