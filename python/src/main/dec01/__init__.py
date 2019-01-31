from aoclib.filereader import get_file_as_string

def get_checksum(digits, distance_to_comparison_digit):
	return sum([
		digits[i]
		for i in range(len(digits))
		if digits[i] == digits[(i+distance_to_comparison_digit) % len(digits)]
	])

def get_checksum_v1(digits):
	return get_checksum(digits, 1)

def get_checksum_v2(digits):
	return get_checksum(digits, len(digits) // 2)

if __name__ == "__main__":
	digits = [int(x) for x in get_file_as_string("input01.txt").strip()]
	print("Captcha part 1:", get_checksum_v1(digits))
	print("Captcha part 2:", get_checksum_v2(digits))
