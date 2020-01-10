using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using Xamrain_APP.Services;
using Xamrain_APP.Views;

namespace Xamrain_APP
{
    public partial class App : Application
    {

        public App()
        {
            InitializeComponent();

            DependencyService.Register<MockDataStore>();
            MainPage = new MainPage();
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
