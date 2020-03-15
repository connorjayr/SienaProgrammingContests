from collections import Counter

word = input()

half_len = len(word) // 2
left = Counter(word[:half_len])
right = Counter(word[-half_len:])

letters = set(word)
majority_sides = [0, 0]
for letter in letters:
  if left[letter] > right[letter]:
    majority_sides[0] += 1
  elif right[letter] > left[letter]:
    majority_sides[1] += 1

print(word)
if sum(majority_sides) == 0:
  print("PERFECTLY BALANCED")
elif majority_sides[0] == majority_sides[1]:
  print("BALANCED")
elif majority_sides[0] > majority_sides[1]:
  print("LEFT UNBALANCED")
elif majority_sides[1] > majority_sides[0]:
  print("RIGHT UNBALANCED")
else:
  exit(1)