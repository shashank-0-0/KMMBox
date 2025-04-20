//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Shashank Shetty on 20/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import MyFramework

@MainActor class ContentsViewModel: ObservableObject {
    
    private var viewModel: ExchangeViewModel
    
    init() {
        self.viewModel = ExchangeViewModel(
            exchangeRepository: CommonExchangeApiModule.echangeModule.exchangeRepository,
            coroutineScope: nil)
        observeExchangeFLow()
    }
    
    func observeExchangeFLow(){
        print("SEEHERE observe flow")
        viewModel.exchangeFlow.subscribe { [weak self] data in
            guard let self = self else{return}
            print("SEEHERE data =  \(data)")
        }
    }

}

final class CommonExchangeApiModule {
    
    static let echangeModule = CommonExchangeModule(client: NetworkApiModule.httpClient)
    
    private init() { }
}

final class NetworkApiModule{
    
    static let networkModule = CommonKtorNetworkModule()
    static let httpClient = networkModule.appHttpClient
    
}
