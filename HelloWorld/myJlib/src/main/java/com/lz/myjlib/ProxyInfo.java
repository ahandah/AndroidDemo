package com.lz.myjlib;

import javax.lang.model.element.TypeElement;

/**
 * Created by RG on 2016/6/2.
 */
public class ProxyInfo {

    String packageName;
    String className;
    String proxyClassName;
    TypeElement classElement;
    int layoutId;

    public static final String PROXY = "PROXY";

    public ProxyInfo(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
        //设置新生成的注解类名
        this.proxyClassName=className+"$$"+PROXY;
    }

    public TypeElement getClassElement() {
        return classElement;
    }

    public void setClassElement(TypeElement classElement) {
        this.classElement = classElement;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public String getProxyClassName() {
        return proxyClassName;
    }

    /**
     * 创建模板代码
     * @return
     */
    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code from ViewInjectProcessor. Do not modify!\n");
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import com.lz.myjlib.Finder;\n");
        builder.append("import com.lz.myjlib.InjectInterface;\n");
        builder.append('\n');
        builder.append("public class ").append(proxyClassName);
        builder.append("<T extends ").append(getClassElement()).append(">");
        builder.append(" implements InjectInterface<T>");
        builder.append(" {\n\n");
        generateInjectMethod(builder);
        builder.append("\n}\n");
        return builder.toString();
    }

    /**
     * 具体方法实现代码
     */
    private void generateInjectMethod(StringBuilder builder) {
        builder.append("  @Override\n");
        builder.append("  public void inject(Finder finder, T target) {");
        if (layoutId > 0) {
            builder.append("    finder.setContentView(target, "+layoutId+");\n");
        }
        builder.append("\n  }\n");
    }
}
