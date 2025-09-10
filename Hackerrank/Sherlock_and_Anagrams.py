from collections import Counter

def sherlockAndAnagrams(s):
    anagram_pairs = 0
    
    for length in range(1, len(s)):
        substring_counts = Counter()
        for i in range(len(s) - length + 1):
            substring = s[i : i + length]
            sorted_substring = "".join(sorted(substring))
            substring_counts[sorted_substring] += 1
            
        for count in substring_counts.values():
            if count > 1:
                anagram_pairs += (count * (count - 1)) // 2                
    return anagram_pairs
