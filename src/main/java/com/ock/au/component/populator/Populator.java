package com.ock.au.component.populator;

import com.ock.au.exception.IllegalResourceException;
import com.ock.au.component.Action;

import java.util.Map;

public interface Populator {
    Map<String, Map<String, Action>> populate() throws IllegalResourceException;
}
