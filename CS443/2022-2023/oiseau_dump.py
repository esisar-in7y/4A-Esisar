import mechanicalsoup

browser = mechanicalsoup.StatefulBrowser()
##url = "http://alpesoiseaux.free.fr/php/listing_sites.php"
url = "https://wiimmfi.de/stats/mkw"
browser.open(url)
print(browser.get_url())
print(" ")
print(browser.get_current_page())
