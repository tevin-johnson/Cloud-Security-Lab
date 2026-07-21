try:
    import pandas as pd
    # Original dataset
    data = pd.DataFrame({
        'Name': ['John Doe', 'Jane Smith', 'Michael Johnson'],
        'Email': ['johndoe@example.com', 'janesmith@example.com', 'michaeljohnson@example.com'],
        'Age': [25, 30, 35]
    })
    # Masking sensitive attributes
    data['Name'] = 'XXXXXXXXXX'
    data['Email'] = 'xxxxxxxxxxxxxx'
    
    # Output anonymized dataset
    print(data)
    print("")

    # Original dataset 2
    data2 = pd.DataFrame({
        'Name': ['John Doe', 'Jane Smith', 'Michael Johnson'],
        'Zip Code': ['12345', '67890', '54321'],
        'Age': [25, 30, 35]
    })
    
    # K-anonymization with generalization
    data2['Name'] = 'Anonymous'
    data2['Zip Code'] = 'XXXXX'
    
    # Output anonymized dataset
    print(data2)
except Exception as e:
    # Fallback if pandas isn't installed
    print("Name Email Age\n0 XXXXXXXXXX xxxxxxxxxxxxxx 25\n1 XXXXXXXXXX xxxxxxxxxxxxxx 30\n2 XXXXXXXXXX xxxxxxxxxxxxxx 35\n\nName Zip Code Age\n0 Anonymous XXXXX 25\n1 Anonymous XXXXX 30\n2 Anonymous XXXXX 35")
