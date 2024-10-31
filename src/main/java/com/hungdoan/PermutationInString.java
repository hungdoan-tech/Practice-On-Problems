package com.hungdoan;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {

        Map<Character, Integer> charToFreq = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charToFreq.put(c, charToFreq.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> originalCharToFreq = new HashMap<>(charToFreq);

        int left = 0;
        boolean inSequence = false;
        for (int right = 0; right < s2.length(); right++) {
            char currentChar = s2.charAt(right);
            Integer freq = charToFreq.get(currentChar);

            if (freq == null) {
                inSequence = false;
                left = right + 1;
                charToFreq = new HashMap<>(originalCharToFreq);
                continue;
            }

            if (inSequence || freq > 0) {
                inSequence = true;

                if (freq == 0) {
                    while (s2.charAt(left) != currentChar) {
                        charToFreq.put(s2.charAt(left), charToFreq.get(s2.charAt(left)) + 1);
                        left++;
                    }
                    left++;
                    continue;
                }

                charToFreq.put(currentChar, freq - 1);
                if (isMapEmpty(charToFreq)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isMapEmpty(Map<Character, Integer> map) {
        for (int freq : map.values()) {
            if (freq != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationInString instance = new PermutationInString();

        System.out.println(instance.checkInclusion("ab", "eidboaoo")); // should return false
        System.out.println(instance.checkInclusion("ab", "eidbaooo")); // should return true
        System.out.println(instance.checkInclusion("adc", "dcda"));    // should return true
    }
}
