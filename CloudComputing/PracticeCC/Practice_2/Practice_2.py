def find_highest(arr, special_array=[], determined_array=[]):
    special_array = set(map(lambda x : x[0], arr))
    determined_array = sorted([max(filter(lambda x : i in x, arr)) for i in special_array])
    return [(i,len(i)) for i in determined_array]

print(find_highest(["a","aa","aaa","b","bbb","bbbbbb"]))  #console output [('aaa', 3), ('bbbbbb', 6)]
print(find_highest(["a","aaaaaaa","z","zzz","zzzzzzz","dddd","d","dd","ddd"])) #console output [('aaaaaaa', 7), ('dddd', 4), ('zzzzzzz', 7)]
