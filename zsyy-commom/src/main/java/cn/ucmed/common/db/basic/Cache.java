package cn.ucmed.common.db.basic;

import java.io.Serializable;

/**
 * redis的简单操作
 */
public class Cache<T> implements Serializable {

  private static final long serialVersionUID = 5267699377911523321L;
  /**
   * @Description
   */

  private Object key;

  public Object getKey() {
    return key;
  }

  private T value;

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public void setKey(Object key) {
    this.key = key;
  }

}
