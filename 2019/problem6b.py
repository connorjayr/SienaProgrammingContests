def assign(word: str, assignment: dict) -> str:
  digits = []
  i = 0
  while i < len(word):
    digits.append(word[i])
    i += 1
  return sum([digits[i] * (10 ** (len(digits) - i - 1)) for i in range(len(digits))])

def find_assignments(rules: list, leading: list):
  return []

words = input().split()
print(words)

rules = []
i = 1
while i <= len(words[-1]):
  rule = []
  for word in words[:-1]:
    if i <= len(word):
      rule.append(word[-i])
  rules.append((*rule, words[-1][-i]))
  i += 1

leading = [word[0] for word in words]

assignments = find_assignments(rules, leading)
assignments.sort(key=lambda a: [assign(word, a) for word in words])

print()