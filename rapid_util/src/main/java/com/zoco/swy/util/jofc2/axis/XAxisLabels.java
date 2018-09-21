/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.zoco.swy.util.jofc2.axis;

import com.zoco.swy.util.jofc2.OFC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author linjunxiong  linjunxiong@zoco.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class XAxisLabels extends Label {

	private static final long serialVersionUID = -6134375829177947590L;
	
	private Integer steps;
    
	private List<Object> labels;
    
	/**
	 * 构建器。
	 */
    public XAxisLabels() {
        //when no labels are needed
    }
    
    /**
     * 构建器。
     * 
     * @param labels String...
     */
    public XAxisLabels(String... labels) {
        addLabels(labels);
    }
    
    /**
     * 构建器。
     * 
     * @param labels List<String>
     */
    public XAxisLabels(List<String> labels) {
        addLabels(OFC.toArray(labels, String.class));
    }
    
    /**
     * 返回labels。
     * 
     * @return List<Object>
     */
    public List<Object> getLabels() {
        return labels;
    }
    
    /**
     * 维护labels。
     * 
     * @param labels String...
     * 
     * @return XAxisLabels
     */
    public XAxisLabels addLabels(String... labels) {
        checkLabels();
        this.labels.addAll(Arrays.asList(labels));
        return this;
    }
    
    /**
     *  维护labels。
     * 
     * @param labels Label...
     * @return XAxisLabels
     */
    public XAxisLabels addLabels(Label... labels) {
        checkLabels();
        this.labels.addAll(Arrays.asList(labels));
        return this;
    }
    
    /**
     * 新增labels。
     * 
     * @param labels List<Label>
     * 
     * @return XAxisLabels
     */
    public XAxisLabels addLabels(List<Label> labels) {
        checkLabels();
        this.labels.addAll(labels);
        return this;
    }
    
    /**
     * 设置steps。
     * 
     * @param steps Integer
     * 
     * @return XAxisLabels
     */
    public XAxisLabels setSteps(Integer steps) {
        this.steps = steps;
        return this;
    }
    
    /**
     * 返回steps。
     * 
     * @return Integer
     */
    public Integer getSteps() {
        return steps;
    }
    
    private synchronized void checkLabels() {
        if (labels == null) {
			labels = new ArrayList<Object>();
		}
    }
    
}
