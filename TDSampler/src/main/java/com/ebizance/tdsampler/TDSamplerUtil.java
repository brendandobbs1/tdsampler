package com.ebizance.tdsampler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import com.ebizance.tdsampler.model.MethodNode;
import com.ebizance.tdsampler.model.Thread;

/**
 * 
 * @author Yannick Robin
 *
 */

public class TDSamplerUtil {

    public static void mergeMethods(Map<String,Integer> methods1, Map<String,Integer> methods2)
    {        
    	for (Iterator <Map.Entry<String, Integer>> it=methods2.entrySet().iterator(); it.hasNext(); )
    	{
			Map.Entry<String, Integer> entry = it.next();
    		String method2 = entry.getKey();
    		Integer counter2 = entry.getValue();
    		
    		if (methods1.containsKey(method2))
    		{
    			Integer counter1 = methods1.get(method2);
    			methods1.put(method2, counter1 + counter2);
    		}
    		else
    		{
    			methods1.put(method2, counter2);
    		}
    	} 
    }

    public static LinkedHashMap<String, Integer> sortHashMapByValues(Map<String, Integer> passedMap, boolean ascending) {
    	final List<String> mapKeys = new ArrayList<String>(passedMap.keySet());
    	final List<Integer> mapValues = new ArrayList<Integer>(passedMap.values());
    	Collections.sort(mapValues);
    	Collections.sort(mapKeys);

    	if (!ascending)
    		Collections.reverse(mapValues);

    	final LinkedHashMap<String, Integer> someMap = new LinkedHashMap<String, Integer>();
    	Iterator<Integer> valueIt = mapValues.iterator();
    	while (valueIt.hasNext()) {
    		Integer val = valueIt.next();
    		final Iterator<String> keyIt = mapKeys.iterator();
	    	while (keyIt.hasNext()) {
	    		String key = keyIt.next();
	    		if (passedMap.get(key).toString().equals(val.toString())) {
	    			passedMap.remove(key);
	    			mapKeys.remove(key);
	    			someMap.put(key, val);
	    			break;
	    		}
    		}
    	}
    	return someMap;
    }
     
    public static int getState(String sState) {	
		int state;
		
    	if (sState.equals("RUNNABLE"))
			state=Thread.STATE_RUNNABLE;		
		else if (sState.equals("WAITING"))
			state=Thread.STATE_WAITING;		
		else if (sState.equals("TIMED_WAITING"))
			state=Thread.STATE_TIMED_WAITING;
		else if (sState.equals("BLOCKED"))
			state=Thread.STATE_BLOCKED;
		else if (sState.equals("IOWAIT"))
			state=Thread.STATE_IOWAIT;
		else
			state=Thread.STATE_UNKNOWN;
		
		return state;
	}
}
