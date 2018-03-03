package app.engine.android.util.SVGImage;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

public class SVGImageRederer {
    GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;
    public SVGImageRederer setContext(Context context) {
        requestBuilder = Glide.with(context)
            .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
            .from(Uri.class)
            .as(SVG.class)
            .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
            .sourceEncoder(new StreamEncoder())
            .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
            .decoder(new SvgDecoder())
            .listener(new SvgSoftwareLayerSetter<Uri>());
        return this;
    }
    public void getImage(String URL, ImageView imageView) {
        Uri uri = Uri.parse(URL);
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .override(10, 10)
                .into(imageView);
    }
}
