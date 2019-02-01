from aoclib.filereader import get_file_as_list_of_string

def is_passphrase_valid_v1(passphrase):
	""" Returns True iff 'passphrase' doesn't contain any repeated words. """
	words = passphrase.split()
	return len(words) == len(set(words))

def is_passphrase_valid_v2(passphrase):
	"""
	Returns True iff 'passphrase' doesn't contain any repeated words, (also considering anagrams).
	"""
	words = passphrase.split()
	return len(words) == len({"".join(sorted(x)) for x in words})

if __name__ == "__main__":
	passphrases = get_file_as_list_of_string("input04.txt")
	print("Valid passphrases (v1):", len([x for x in passphrases if is_passphrase_valid_v1(x)]))
	print("Valid passphrases (v2):", len([x for x in passphrases if is_passphrase_valid_v2(x)]))
