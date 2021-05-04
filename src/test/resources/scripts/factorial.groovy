def factorial(n) {
  if (n <= 0)
    throw new IllegalArgumentException("n must be a natural number")
      
  if (n == 1)
    return 1
      
  return n * factorial(n - 1)
}