package com.test.interpolation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Interpolation {

    public static JSONArray shapes;
    public static Map<Integer, JSONObject> shapesMap; // tracks中frameIndex:shape
    public static ArrayList<Integer> frames = new ArrayList<>(); // tracks中index数组
    static {
        for (int i=0;i<shapes.size();i++){
            JSONObject item = shapes.getJSONObject(i);
            int keyframe = item.getIntValue("frame");
            shapesMap.put(keyframe, item);
            frames.add(keyframe);
        }
    }

    // rectangleTrack插值：根据上一个坐标和下一个坐标计算当前坐标
    public static JSONObject interpolation(JSONObject leftPosition, JSONObject rightPosition, double offset){
        JSONArray leftPoints = leftPosition.getJSONArray("points");
        JSONArray rightPoints = rightPosition.getJSONArray("points");
        JSONArray points = new JSONArray();
        for( int i=0; i<leftPoints.size();i++){ // rect: size=4
            double positionOffset = rightPoints.getDoubleValue(i)-leftPoints.getDoubleValue(i);
            double point = leftPoints.getDoubleValue(i) + positionOffset * offset;
            points.add(point);
        }
        JSONObject result = new JSONObject();
        result.put("points", points);
        result.put("occluded", leftPosition.getBooleanValue("occluded"));
        result.put("outside", leftPosition.getBooleanValue("outside"));
        return result;
    }

    public static JSONObject getPosition(int targetFrame, int leftKeyframe, int rightFrame){
        int leftFrame = shapesMap.containsKey(targetFrame) ? targetFrame : leftKeyframe;
        JSONObject rightPosition = shapesMap.get(rightFrame);
        JSONObject leftPosition = shapesMap.get(leftFrame);
        JSONObject result = new JSONObject();
        if(leftPosition != null && rightPosition != null){
            result.putAll(interpolation(leftPosition, rightPosition, (targetFrame -leftFrame)/(rightFrame-leftFrame)));
            result.put("keyframe", shapesMap.containsKey(targetFrame));
        }
        if(leftPosition != null ){
            result.put("points",leftPosition.getJSONArray("points"));
            result.put("occluded",leftPosition.getBooleanValue("occluded"));
            result.put("outside",leftPosition.getBooleanValue("outside"));
            result.put("keyframe",shapesMap.containsKey(targetFrame));
        }
        if(rightPosition != null){
            result.put("points",rightPosition.getJSONArray("points"));
            result.put("occluded",rightPosition.getBooleanValue("occluded"));
            result.put("outside",rightPosition.getBooleanValue("outside"));
            result.put("keyframe",shapesMap.containsKey(targetFrame));
        }
        return result;
    }
    public static JSONObject boundedKeyframes( int targetFrame){
        int lDiff = Integer.MAX_VALUE;
        int rDiff = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE;
        int last = Integer.MIN_VALUE;

        for (Integer frame : frames) {
            if(frame < first){
                first = frame;
            }
            if(frame > last){
                last = frame;
            }
            int diff = Math.abs(targetFrame - frame);
            if(frame < targetFrame && diff < lDiff){
                lDiff = diff;
            }else if(frame > targetFrame && diff < rDiff){
                rDiff = diff;
            }
        }
        int prev = lDiff == Integer.MAX_VALUE ? null : targetFrame-lDiff;
        int next = rDiff == Integer.MAX_VALUE ? null : targetFrame-rDiff;

        JSONObject result = new JSONObject();
        result.put("prev", prev);
        result.put("next", next);
        result.put("first", first);
        result.put("last", last);
        return result;
    }
    public static JSONObject get(int frame){ // 当前帧处理帧frame
        JSONObject curr = boundedKeyframes(frame);
        int prev = curr.getIntValue("prev");
        int next = curr.getIntValue("next");
        int first = curr.getIntValue("first");
        int last = curr.getIntValue("last");

        JSONObject result = new JSONObject();
        result.putAll(getPosition(frame, prev, next));
        result.put("keyframes",curr);
        result.put("frame", frame);

        return result;
    }


}
