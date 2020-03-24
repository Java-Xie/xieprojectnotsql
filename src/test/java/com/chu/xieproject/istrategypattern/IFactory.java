package com.chu.xieproject.istrategypattern;

/**
 * @title: IFactory
 * @author: xiezhiqiang
 * @date 2020/3/23 15:15
 */
public class IFactory {

    public static Strategy newStrategy(String className){
        try {
            Class<?> clazz = Class.forName(className);
            return (Strategy) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
