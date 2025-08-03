function Footer() {
    return (
      <footer className="bg-orange-600 text-white py-6">
        <div className="max-w-7xl mx-auto px-4 flex flex-col md:flex-row justify-between items-center">
          <p className="text-sm text-center md:text-left mb-4 md:mb-0">
            © {new Date().getFullYear()} Aahar. All rights reserved.
          </p>
          <div className="flex space-x-4">
            <a href="/about" className="hover:underline hover:text-orange-100 text-sm">
              About Us
            </a>
            <a href="/contact" className="hover:underline hover:text-orange-100 text-sm">
              Contact Us
            </a>
            <a href="/privacy" className="hover:underline hover:text-orange-100 text-sm">
              Privacy Policy
            </a>
          </div>
        </div>
      </footer>
    );
  }
  