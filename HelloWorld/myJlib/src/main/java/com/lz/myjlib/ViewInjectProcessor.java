package com.lz.myjlib;

import java.util.HashMap;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import android.view.*;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2018/3/7.
 */
@SupportedAnnotationTypes("com.lz.myjib")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ViewInjectProcessor extends AbstractProcessor {

    HashMap<String, ProxyInfo> proxyInfoHashMap;
    String fqClassName, className, packageName;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        proxyInfoHashMap = new HashMap<>();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(ViewInject.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement classElement = (TypeElement) element;
                PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();
                fqClassName = classElement.getQualifiedName().toString();
                className = classElement.getSimpleName().toString();
                packageName = packageElement.getQualifiedName().toString();
                int layoutId = classElement.getAnnotation(ViewInject.class).value();

                if (proxyInfoHashMap.containsKey(fqClassName)) {
                    proxyInfoHashMap.get(fqClassName).setLayoutId(layoutId);
                } else {
                    ProxyInfo proxyInfo=new ProxyInfo(packageName, className);
                    proxyInfo.setClassElement(classElement);
                    proxyInfo.setLayoutId(layoutId);
                    proxyInfoHashMap.put(fqClassName, proxyInfo);
                }

            }
        }
        return true;
    }
}
